package com.siftscience;

import com.siftscience.model.VerificationCheckFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class VerificationCheckRequestTest {

    @Test
    public void testVerification() throws Exception {
        String expectedRequestBody = "{\n" +
                "    \"$user_id\" : \"billy_jones_301\",\n" +
                "    \"$code\": \"123456\",\n" +
                "    \"$verified_event\": \"$login\",\n" +
                "    \"$verified_entity_id\": \"SOME_SESSION_ID\"\n" +
                "  }";
                // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody("{\n" +
                "  \"status\": 0,\n" +
                "  \"error_message\": \"OK\",\n" +
                "  \"sent_at\": 1566324368002\n" +
                "}");
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        VerificationCheckRequest request = client.buildRequest(
                new VerificationCheckFieldSet()
                        .setCode("123456")
                        .setUserId("billy_jones_301")
                        .setVerifiedEvent("$login")
                        .setVerifiedEntityId("SOME_SESSION_ID")
        );

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();

    }
}
