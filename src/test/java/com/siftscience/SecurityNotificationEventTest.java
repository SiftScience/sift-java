package com.siftscience;

import com.siftscience.model.SecurityNotificationFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Unit tests around the Security notification events.
 */
public class SecurityNotificationEventTest {

    @Test
    public void testSecurityNotification() throws Exception {
        String sessionId = "gigtleqddo84l8cm15qe4il";
        String verifiedValue = "14155551212";

        String expectedRequestBody = "{\n" +
                "  \"$type\"         : \"$security_notification\",\n" +
                "  \"$api_key\"      : \"your_api_key_here\",\n" +
                "  \"$user_id\"      : \"billy_jones_301\",\n" +
                "  \"$session_id\"   : \"" + sessionId + "\",\n" +
                "  \"$notification_status\"       : \"$sent\",\n" +
                "  \"$notification_type\" : \"$sms\",\n" +
                "  \"$notified_value\" : \"" + verifiedValue + "\"\n" +
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
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key_here");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        SecurityNotificationFieldSet fieldSet = new SecurityNotificationFieldSet()
                .setUserId("billy_jones_301")
                .setSessionId(sessionId)
                .setNotificationStatus("$sent")
                .setNotificationType("$sms")
                .setNotifiedValue(verifiedValue);
        SiftRequest request = client.buildRequest(fieldSet);

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v204/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();

    }
}
