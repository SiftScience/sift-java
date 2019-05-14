package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.App;
import com.siftscience.model.VerificationFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class VerificationEventTest {

    @Test
    public void testVerification() throws Exception {
        String sessionId = "gigtleqddo84l8cm15qe4il";
        String verifiedValue = "14155551212";
        String verifiedEvent = "$create_content";
        String verifiedEntityId = "chekle212452";
        String reason = "$user_setting";

        String expectedRequestBody = "{\n" +
                "  \"$type\"         : \"$verification\",\n" +
                "  \"$api_key\"      : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"      : \"billy_jones_301\",\n" +
                "  \"$session_id\"   : \"" + sessionId + "\",\n" +
                "  \"$app\"          : {\n" +
                "      \"$os\"       : \"iOS\",\n" +
                "      \"$app_name\" : \"Calculator\",\n" +
                "      \"$device_manufacturer\" : \"Apple\",\n" +
                "      \"$device_model\" : \"iPhone 4,2\",\n" +
                "      \"$device_unique_id\" : \"A3D261E4-DE0A-470B-9E4A-720F3D3D22E6\",\n" +
                "      \"$app_version\" : \"3.2.7\",\n" +
                "  },\n" +
                "  \"$status\"       : \"$pending\",\n" +
                "  \"$verification_type\" : \"$sms\",\n" +
                "  \"$verified_value\" : \"" + verifiedValue + "\",\n" +
                "  \"$verified_event\" : \"" + verifiedEvent + "\",\n" +
                "  \"$verified_entity_id\" : \"" + verifiedEntityId + "\",\n" +
                "  \"$reason\" : \"" + reason + "\",\n" +
                "  \"$ip\" : \"128.148.1.135\",\n" +
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
        SiftRequest request = client.buildRequest(new VerificationFieldSet()
                .setUserId("billy_jones_301")
                .setSessionId(sessionId)
                .setStatus("$pending")
                .setVerificationType("$sms")
                .setVerifiedValue(verifiedValue)
                .setReason(reason)
                .setVerifiedEvent(verifiedEvent)
                .setVerifiedEntityId(verifiedEntityId)
                .setIp("128.148.1.135")
                .setApp(new App()
                        .setAppName("Calculator")
                        .setAppVersion("3.2.7")
                        .setDeviceManufacturer("Apple")
                        .setDeviceModel("iPhone 4,2")
                        .setDeviceUniqueId("A3D261E4-DE0A-470B-9E4A-720F3D3D22E6")
                        .setOperatingSystem("iOS")));

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
