package com.siftscience;

import com.siftscience.model.GetDecisionFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class GetDecisionsTest {
    @Test
    public void testDecisionStatus() throws Exception {
        String responseBody = "{\"decisions\": " +
                    "[" +
                        "{\"id\":\"user_is_good\"}," +
                        "{\"id\":\"user_is_bad\"}," +
                        "{\"id\":\"user_is_ugly\"}" +
                    "]" +
                "}";


        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);

        response.setBody(responseBody);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl;
        baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseApi3Url(baseUrl);

        // Build and execute the request against the mock server.
        GetDecisionsRequest getDecisionsRequest = client.buildRequest(
                new GetDecisionFieldSet().setAccountId("your_account_id"));
        GetDecisionsResponse siftResponse = getDecisionsRequest.send();

        // Verify the request.
        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/v3/accounts/your_account_id/decisions", request.getPath());
        Assert.assertEquals(request.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }
}
