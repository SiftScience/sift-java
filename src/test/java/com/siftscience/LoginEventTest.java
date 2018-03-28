package com.siftscience;

import com.siftscience.model.App;
import com.siftscience.model.Browser;
import com.siftscience.model.LoginFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginEventTest {

    @Test
    public void testLoginWithApp() throws Exception {
        String operatingSystem = "iOS";
        String appName = "Calculator";

        String expectedRequestBody = "{\n" +
                "  \"$type\"         : \"$login\",\n" +
                "  \"$api_key\"      : \"your_api_key_here\",\n" +
                "  \"$user_id\"      : \"billy_jones_301\",\n" +
                "  \"$login_status\" : \"$success\",\n" +
                "  \"$app\"          : {\n" +
                "      \"$os\"       : \"" + operatingSystem + "\",\n" +
                "      \"$app_name\" : \"" + appName + "\"\n" +
                "  }\n" +
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
        SiftRequest request = client.buildRequest(new LoginFieldSet()
                .setUserId("billy_jones_301")
                .setLoginStatus("$success")
                .setApp(new App()
                        .setOperatingSystem(operatingSystem)
                        .setAppName(appName)));

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
    public void testLoginWithBrowswer() throws Exception {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3)";

        String expectedRequestBody = "{\n" +
                "  \"$type\"         : \"$login\",\n" +
                "  \"$api_key\"      : \"your_api_key_here\",\n" +
                "  \"$user_id\"      : \"billy_jones_301\",\n" +
                "  \"$login_status\" : \"$success\",\n" +
                "  \"$browser\"          : {\n" +
                "      \"$user_agent\"       : \"" + userAgent + "\"\n" +
                "  }\n" +
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
        SiftRequest request = client.buildRequest(new LoginFieldSet()
                .setUserId("billy_jones_301")
                .setLoginStatus("$success")
                .setBrowser(new Browser()
                        .setUserAgent(userAgent)));

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
