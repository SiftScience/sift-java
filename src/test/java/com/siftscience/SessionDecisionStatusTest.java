package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.DecisionStatusFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class SessionDecisionStatusTest {


    @Test
    public void testSessionDecisionStatus() throws Exception {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);

        String rspBody = "{\n" +
            "  \"decisions\": {\n" +
            "    \"account_takeover\": {\n" +
            "      \"decision\": {\n" +
            "        \"id\": \"session_decision\"\n" +
            "      },\n" +
            "      \"time\": 1461963429151,\n" +
            "      \"webhook_succeeded\": true\n" +
            "    }\n" +
            "  }\n" +
            "}";

        response.setBody(rspBody);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl;
        baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseApi3Url(baseUrl);

        // Build and execute the request against the mock server.
        DecisionStatusRequest request = client.buildRequest(
            new DecisionStatusFieldSet()
                .setAccountId("your_account_id")
                .setUserId("someuser")
                .setEntity(DecisionStatusFieldSet.ENTITY_SESSION)
                .setEntityId("someid"));
        DecisionStatusResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("GET", request1.getMethod());
        Assert.assertEquals("/v3/accounts/your_account_id/users/someuser/session/someid/decisions",
            request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
            siftResponse.getBody().toJson(), true);
    }
}
