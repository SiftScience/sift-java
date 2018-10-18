package com.siftscience;

import com.siftscience.model.App;
import com.siftscience.model.Browser;
import com.siftscience.model.CustomEventFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class CustomEventTest {

    @Test
    public void testCustomEventWithApp() throws Exception {
        String operatingSystem = "iOS";
        String appName = "Calculator";
        String expectedRequestBody = "{\n" +
                "  \"$type\"              : \"make_call\",\n" +
                "  \"$api_key\"           : \"your_api_key_here\",\n" +
                "  \"$user_id\"           : \"billy_jones_301\",\n" +
                "  \"recipient_user_id\"  : \"marylee819\",\n" +
                "  \"call_duration\"      : 4428,\n" +
                "  \"$app\"          : {\n" +
                "      \"$os\"       : \"" + operatingSystem + "\",\n" +
                "      \"$app_name\" : \"" + appName + "\"\n" +
                "   }\n" +
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
        SiftRequest request = client.buildRequest(new CustomEventFieldSet()
                .setEventType("make_call")
                .setUserId("billy_jones_301")
                .setApp(new App().setAppName(appName)
                    .setOperatingSystem(operatingSystem))
                .setCustomField("recipient_user_id", "marylee819")
                .setCustomField("call_duration", 4428));

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

    @Test
    public void testCustomEventWithBrowser() throws Exception {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3)";
        String expectedRequestBody = "{\n" +
                "  \"$type\"              : \"make_call\",\n" +
                "  \"$api_key\"           : \"your_api_key_here\",\n" +
                "  \"$user_id\"           : \"billy_jones_301\",\n" +
                "  \"recipient_user_id\"  : \"marylee819\",\n" +
                "  \"call_duration\"      : 4428,\n" +
                "  \"$browser\"          : {\n" +
                "      \"$user_agent\"       : \"" + userAgent + "\"\n" +
                "   }\n" +
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
        SiftRequest request = client.buildRequest(new CustomEventFieldSet()
                .setEventType("make_call")
                .setUserId("billy_jones_301")
                .setBrowser(new Browser().setUserAgent(userAgent))
                .setCustomField("recipient_user_id", "marylee819")
                .setCustomField("call_duration", 4428));

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
