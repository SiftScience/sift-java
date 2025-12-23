package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.siftscience.model.EventResponseBody;
import com.siftscience.model.FlagContentFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(Parameterized.class)
public class FlagContentEventTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        FlagContentFieldSet.FlagContentReason[] values = FlagContentFieldSet.FlagContentReason.values();
        List<Object[]> data = new ArrayList<>(values.length);
        for (FlagContentFieldSet.FlagContentReason reason : values) {
            data.add(new Object[] { reason });
        }
        return data;
    }

    private final FlagContentFieldSet.FlagContentReason reason;

    public FlagContentEventTest(FlagContentFieldSet.FlagContentReason reason) {
        this.reason = reason;
    }
    @Test
    public void testFlagContent() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"       : \"$flag_content\", \n" +
                "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
                "  \"$brand_name\" : \"jamieli98\",\n" +
                "  \"$user_id\"    : \"billy_jones_301\",\n" +
                "  \"$content_id\" : \"9671500641\",\n" +
                "\n" +
                "  \"$flagged_by\" : \"jamieli89\",\n" +
                "  \"$reason\" : \"" + this.reason.value + "\",\n" +
                "  \"$site_country\" : \"UA\",\n" +
                "  \"$site_domain\" : \"example.com\",\n" +
                "  \"$user_email\" : \"billy_jones_301@email.com\",\n" +
                "  \"$verification_phone_number\" : \"+12345678901\"\n" +
                "}";


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
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        SiftRequest<EventResponse> request = client.buildRequest(new FlagContentFieldSet()
                .setUserId("billy_jones_301")
                .setContentId("9671500641")
                .setFlaggedBy("jamieli89")
                .setReason(this.reason)
                .setBrandName("jamieli98")
                .setSiteCountry("UA")
                .setSiteDomain("example.com")
                .setUserEmail("billy_jones_301@email.com")
                .setVerificationPhoneNumber("+12345678901"));

        SiftResponse<EventResponseBody> siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertNotNull(siftResponse.getBody());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
