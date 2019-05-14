package com.siftscience;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;

import com.siftscience.model.UnlabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;

public class UnlabelTest {
    @Test
    public void testUnlabel() throws Exception {

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_NO_CONTENT);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UnlabelFieldSet()
                .setUserId("billy_jones_301").setAbuseType("payment_abuse"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("DELETE", request1.getMethod());
        Assert.assertEquals("/v205/users/billy_jones_301/labels?api_key=YOUR_API_KEY" +
                        "&abuse_type=payment_abuse", request1.getPath());

        // Verify the response.
        Assert.assertEquals(HTTP_NO_CONTENT, siftResponse.getHttpStatusCode());

        server.shutdown();
    }

    @Test
    public void testUnlabelAllAbuseTypes() throws Exception {

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_NO_CONTENT);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UnlabelFieldSet()
                .setUserId("billy_jones_301"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("DELETE", request1.getMethod());
        Assert.assertEquals("/v205/users/billy_jones_301/labels?api_key=YOUR_API_KEY",
                request1.getPath());

        // Verify the response.
        Assert.assertEquals(HTTP_NO_CONTENT, siftResponse.getHttpStatusCode());

        server.shutdown();
    }
}
