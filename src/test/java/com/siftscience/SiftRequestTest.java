package com.siftscience;

import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import static java.net.HttpURLConnection.HTTP_OK;

public class SiftRequestTest {

    @Test
    public void testUserAgentHeader() throws Exception {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);

        server.enqueue(response);
        server.start();
        String userId = "a_user_id";

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        // Build and execute the request against the mock server.
        ApplyDecisionRequest request = client.buildRequest(
                new ApplyDecisionFieldSet()
                        .setUserId(userId)
                        .setTime(System.currentTimeMillis()));

        request.send();

        // Verify the request.
        RecordedRequest recordedRequest = server.takeRequest();
        Assert.assertEquals("SiftScience/v205 sift-java/3.7.0", recordedRequest.getHeader("User-Agent"));
    }

}
