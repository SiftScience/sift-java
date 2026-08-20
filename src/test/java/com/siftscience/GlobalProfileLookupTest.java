package com.siftscience;

import com.siftscience.exception.MissingFieldException;
import com.siftscience.model.GlobalProfileLookupFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class GlobalProfileLookupTest {

    @Test
    public void testGlobalProfileLookupByEmail() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";
        String responseBody = "{\n" +
                "  \"status\": 0,\n" +
                "  \"error_message\": \"OK\",\n" +
                "  \"lookback_months\": 12,\n" +
                "  \"profile_summary\": {\n" +
                "    \"identity_found\": true,\n" +
                "    \"has_links\": true,\n" +
                "    \"link_count\": 7,\n" +
                "    \"linked_accounts_count_per_industry\": {\"finances\": 3, \"internet\": 4}\n" +
                "  }\n" +
                "}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);
        server.enqueue(response);
        server.start();

        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        GlobalProfileLookupRequest lookupRequest = client.buildRequest(
                new GlobalProfileLookupFieldSet()
                        .setEmail("jane.doe@example.com"));

        GlobalProfileLookupResponse siftResponse = lookupRequest.send();

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("POST", request.getMethod());
        Assert.assertEquals("/v3/accounts/" + accountId + "/global_profile/lookup",
                request.getPath());
        Assert.assertEquals(request.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");
        JSONAssert.assertEquals("{\"email\": \"jane.doe@example.com\"}",
                request.getBody().readUtf8(), false);

        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
        Assert.assertTrue(siftResponse.getBody().getProfileSummary().getIdentityFound());
    }

    @Test
    public void testGlobalProfileLookupByPhone() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";
        String responseBody = "{\"status\": 0, \"error_message\": \"OK\", \"error_code\": null, " +
                "\"profile_summary\": {\"identity_found\": false}}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);
        server.enqueue(response);
        server.start();

        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        GlobalProfileLookupRequest lookupRequest = client.buildRequest(
                new GlobalProfileLookupFieldSet()
                        .setPhone("+15551234567"));
        GlobalProfileLookupResponse siftResponse = lookupRequest.send();

        RecordedRequest request = server.takeRequest();
        JSONAssert.assertEquals("{\"phone\": \"+15551234567\"}",
                request.getBody().readUtf8(), false);

        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertTrue(siftResponse.isOk());
        Assert.assertFalse(siftResponse.getBody().getProfileSummary().getIdentityFound());
    }

    @Test
    public void testLookupIdentityNotFound() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";
        String responseBody = "{\n" +
                "  \"status\": 0,\n" +
                "  \"error_message\": \"OK\",\n" +
                "  \"error_code\": null,\n" +
                "  \"lookback_months\": null,\n" +
                "  \"profile_summary\": {\n" +
                "    \"identity_found\": false,\n" +
                "    \"has_links\": null,\n" +
                "    \"link_count\": null,\n" +
                "    \"linked_accounts_count_per_industry\": null\n" +
                "  },\n" +
                "  \"identity_age\": null,\n" +
                "  \"user_decisions\": null,\n" +
                "  \"chargebacks\": null,\n" +
                "  \"orders\": null,\n" +
                "  \"transactions\": null,\n" +
                "  \"locations\": null\n" +
                "}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);
        server.enqueue(response);
        server.start();

        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        GlobalProfileLookupRequest lookupRequest = client.buildRequest(
                new GlobalProfileLookupFieldSet()
                        .setEmail("unknown@example.com"));
        GlobalProfileLookupResponse siftResponse = lookupRequest.send();

        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertTrue(siftResponse.isOk());
        Assert.assertFalse(siftResponse.getBody().getProfileSummary().getIdentityFound());
        Assert.assertNull(siftResponse.getBody().getLookbackMonths());
        Assert.assertNull(siftResponse.getBody().getIdentityAge());
        Assert.assertNull(siftResponse.getBody().getUserDecisions());
        Assert.assertNull(siftResponse.getBody().getChargebacks());
        Assert.assertNull(siftResponse.getBody().getOrders());
        Assert.assertNull(siftResponse.getBody().getTransactions());
        Assert.assertNull(siftResponse.getBody().getLocations());
    }

    @Test
    public void testGlobalProfileLookupRequiresEmailOrPhone() {
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID");

        GlobalProfileLookupRequest lookupRequest = client.buildRequest(
                new GlobalProfileLookupFieldSet());

        try {
            lookupRequest.send();
            Assert.fail("Expected a MissingFieldException to be thrown");
        } catch (MissingFieldException e) {
            // expected
        } catch (Exception e) {
            Assert.fail("Expected a MissingFieldException, got " + e.getClass());
        }
    }
}
