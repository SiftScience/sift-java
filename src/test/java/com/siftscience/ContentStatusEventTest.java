package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.ContentStatusFieldSet;
import com.siftscience.model.EventResponseBody;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class ContentStatusEventTest {
    @Test
    public void testContentStatus() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"       : \"$content_status\",\n" +
                "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"    : \"billy_jones_301\",\n" +
                "  \"$content_id\" : \"9671500641\",\n" +
                "  \"$status\"     : \"$paused\",\n" +
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
        SiftRequest<EventResponse> request = client.buildRequest(new ContentStatusFieldSet()
                .setUserId("billy_jones_301")
                .setContentId("9671500641")
                .setStatus("$paused")
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
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        Assert.assertNotNull(response.getBody());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
