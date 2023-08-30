package com.siftscience;

import com.siftscience.model.Browser;
import com.siftscience.model.Event;
import com.siftscience.model.VerificationResponseBody;
import com.siftscience.model.VerificationSendFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class VerificationSendRequestTest {

    @Test
    public void testVerification() throws Exception {
        String sessionId = "gigtleqddo84l8cm15qe4il";
        String expectedRequestBody = "{\n" +
                "  \"$user_id\": \"billy_jones_301\",\n" +
                "  \"$send_to\": \"billy_jones_301@gmail.com\",\n" +
                "  \"$verification_type\": \"$email\",\n" +
                "  \"$brand_name\": \"nike\",\n" +
                "  \"$language\": \"en\",\n" +
                "  \"$event\": {\n" +
                "    \"$session_id\": \"gigtleqddo84l8cm15qe4il\",\n" +
                "    \"$verified_event\": \"$login\",\n" +
                "    \"$reason\": \"$automated_rule\",\n" +
                "    \"$ip\": \"192.168.1.1\",\n" +
                "    \"$browser\": {\n" +
                "      \"$user_agent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
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

        VerificationSendRequest request = client.buildRequest(new VerificationSendFieldSet()
                .setUserId("billy_jones_301")
                .setSendTo("billy_jones_301@gmail.com")
                .setVerificationType("$email")
                .setBrandName("nike")
                .setLanguage("en")
                .setVerificationEvent(
                        new Event()
                                .setSessionId(sessionId)
                                .setVerifiedEvent("$login")
                                .setReason("$automated_rule")
                                .setIp("192.168.1.1")
                                .setBrowser(
                                        new Browser()
                                                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36" +
                                                        " (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                                )

                )
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
