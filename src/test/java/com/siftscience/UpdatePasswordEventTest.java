package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.App;
import com.siftscience.model.EventResponseBody;
import com.siftscience.model.UpdatePasswordFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 * Unit tests around the Update_password events.
 */

public class UpdatePasswordEventTest {
    @Test
    public void testUpdatePassword() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"         : \"$update_password\",\n" +
                "  \"$api_key\"      : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"      : \"billy_jones_301\",\n" +
                "  \"$session_id\" : \"gigtleqddo84l8cm15qe4il\",\n" +
                "  \"$app\"          : {\n" +
                "      \"$os\"       : \"iOS\",\n" +
                "      \"$app_name\" : \"Calculator\",\n" +
                "      \"$device_manufacturer\" : \"Apple\",\n" +
                "      \"$device_model\" : \"iPhone 4,2\",\n" +
                "      \"$device_unique_id\" : \"A3D261E4-DE0A-470B-9E4A-720F3D3D22E6\",\n" +
                "      \"$app_version\" : \"3.2.7\"\n" +
                "  },\n" +
                " \"$reason\" : \"$forced_reset\",\n" +
                " \"$status\" : \"$success\",\n" +
                " \"$ip\" : \"128.148.1.135\",\n" +
                " \"$user_email\" : \"billy_jones_301@email.com\",\n" +
                " \"$verification_phone_number\" : \"+12345678901\"\n" +
                "}";

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
        UpdatePasswordFieldSet fieldSet = new UpdatePasswordFieldSet()
                .setUserId("billy_jones_301")
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setReason("$forced_reset")
                .setStatus("$success")
                .setIp("128.148.1.135")
                .setUserEmail("billy_jones_301@email.com")
                .setVerificationPhoneNumber("+12345678901")
                .setApp(new App()
                        .setAppName("Calculator")
                        .setAppVersion("3.2.7")
                        .setDeviceManufacturer("Apple")
                        .setDeviceModel("iPhone 4,2")
                        .setDeviceUniqueId("A3D261E4-DE0A-470B-9E4A-720F3D3D22E6")
                        .setOperatingSystem("iOS"));

        SiftRequest<EventResponse> request = client.buildRequest(fieldSet);
        SiftResponse<EventResponseBody> siftResponse = request.send();

        //Verify the request
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertNotNull(siftResponse.getBody());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(), siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
