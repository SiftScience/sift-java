package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.CustomEventFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class CustomEventTest {
    @Test
    public void testCustomEvent() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"              : \"make_call\",\n" +
                "  \"$api_key\"           : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"           : \"billy_jones_301\",\n" +
                "  \"recipient_user_id\"  : \"marylee819\",\n" +
                "  \"$site_country\": \"US\",\n" +
                "  \"$site_domain\": \"sift.com\",\n" +
                "  \"$keyless_user_id\": \"keylessUserId-123\",\n" +
                "  \"$brand_name\": \"sift\",\n" +
                "  \"$user_email\": \"sift@sift.com\",\n" +
                "  \"$name\": \"Sift\",\n" +
                "  \"$phone\": \"(415) 882-7709\",\n" +
                "  \"call_duration\"      : 4428,\n" +
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
        SiftRequest request = client.buildRequest(new CustomEventFieldSet()
                .setEventType("make_call")
                .setUserId("billy_jones_301")
                .setSiteCountry("US")
                .setSiteDomain("sift.com")
                .setKeylessUserId("keylessUserId-123")
                .setBrandName("sift")
                .setUserEmail("sift@sift.com")
                .setName("Sift")
                .setPhone("(415) 882-7709")
                .setCustomField("recipient_user_id", "marylee819")
                .setCustomField("call_duration", 4428)
                .setVerificationPhoneNumber("+12345678901"));

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
}
