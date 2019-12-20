package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;
import com.siftscience.model.BaseAppBrowserFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class BaseSiteBrandFieldSetTest {
    private static final String DUMMY_API_KEY = "YOUR_API_KEY";
    private static final String DUMMY_ACCOUNT_ID = "YOUR_ACCOUNT_ID";
    private static final String DUMMY_USERID = "billy_jones_301";
    private static final String REQUEST_BODY_TEMPLATE = "{\n" +
        "\"$api_key\"   : \"" + DUMMY_API_KEY + "\",\n" +
        "\"$user_id\"   : \"" + DUMMY_USERID + "\"" +
        "%s" +
        "\n}";

    @Test
    public void testAllSubclasses() {
        Class<?> [] subclasses = {
            BaseAppBrowserFieldSet.class
        };

        for (Class<?> subclass : subclasses) {
            assertThat(subclass, typeCompatibleWith(BaseAppBrowserFieldSet.class));
        }
    }

    @Test
    public void testSiteCountry() throws Exception {
        String siteCountry = "US";
        test(
            new BaseSiteBrandFieldSetTest.TestFieldSet()
                .setUserId(DUMMY_USERID)
                .setSiteCountry(siteCountry),
            String.format(REQUEST_BODY_TEMPLATE, ", \"$site_country\" : \"US\"  }\n")
        );
    }

    @Test
    public void testSiteDomain() throws Exception {
        String siteDomain = "sift.com";
        test(
            new BaseSiteBrandFieldSetTest.TestFieldSet()
                .setUserId(DUMMY_USERID)
                .setSiteDomain(siteDomain),
            String.format(REQUEST_BODY_TEMPLATE, ", \"$site_domain\" : \"sift.com\"  }\n")
        );
    }

    @Test
    public void testBrandName() throws Exception {
        String brandName = "sift";
        test(
            new BaseSiteBrandFieldSetTest.TestFieldSet()
                .setUserId(DUMMY_USERID)
                .setBrandName(brandName),
            String.format(REQUEST_BODY_TEMPLATE, ", \"$brand_name\" : \"sift\"  }\n")
        );
    }

    @Test
    public void testNoSiteBrandFields() throws Exception {
        test(
            new BaseSiteBrandFieldSetTest.TestFieldSet().setUserId(DUMMY_USERID),
            String.format(REQUEST_BODY_TEMPLATE, "")
        );
    }

    private void test(BaseSiteBrandFieldSetTest.TestFieldSet t, String expectedRequestBody) throws Exception {
        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody("{\n" +
            "    \"status\" : 0,\n" +
            "    \"error_message\" : \"OK\",\n" +
            "    \"time\" : 1327604222,\n" +
            "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
            "}");
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient(DUMMY_API_KEY, DUMMY_ACCOUNT_ID,
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        SiftRequest request = client.buildRequest(t);
        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
            siftResponse.getBody().toJson(), true);

        server.shutdown();
    }

    private static class TestFieldSet extends BaseAppBrowserFieldSet<BaseSiteBrandFieldSetTest.TestFieldSet> {
        public BaseSiteBrandFieldSetTest.TestFieldSet fromJson(String json) {
            return gson.fromJson(json, BaseSiteBrandFieldSetTest.TestFieldSet.class);
        }
    }
}
