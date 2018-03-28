package com.siftscience;

import com.siftscience.model.ChargebackFieldSet;
import com.siftscience.model.UnlabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;

public class UnlabelTest {
    @Test
    public void testUnlabel() throws Exception {

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_NO_CONTENT);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("23b87a99k099fc98");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UnlabelFieldSet()
                .setUserId("billy_jones_301").setAbuseType("payment_abuse"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("DELETE", request1.getMethod());
        Assert.assertEquals("/v205/users/billy_jones_301/labels?api_key=23b87a99k099fc98" +
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
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("23b87a99k099fc98");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UnlabelFieldSet()
                .setUserId("billy_jones_301"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("DELETE", request1.getMethod());
        Assert.assertEquals("/v205/users/billy_jones_301/labels?api_key=23b87a99k099fc98",
                request1.getPath());

        // Verify the response.
        Assert.assertEquals(HTTP_NO_CONTENT, siftResponse.getHttpStatusCode());

        server.shutdown();
    }
}
