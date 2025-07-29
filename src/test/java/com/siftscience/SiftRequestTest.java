package com.siftscience;

import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import com.siftscience.exception.SiftException;
import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Test;

public class SiftRequestTest {

    @Test
    public void testUserAgentHeader() throws Exception {
        // given
        MockWebServer server = new MockWebServer();
        setUpMockResponseWithCode(server, HTTP_OK);
        ApplyDecisionRequest request = createRequest(server, "a_user_id");

        // when
        request.send();

        // then
        RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("SiftScience/v205 sift-java/3.21.1",
            recordedRequest.getHeader("User-Agent"));
    }

    @Test
    public void testEnqueueRequests() throws Exception {
        // given
        MockWebServer server = new MockWebServer();
        setUpMockResponseWithCode(server, HTTP_OK);
        ApplyDecisionRequest request = createRequest(server, "a_user_id");

        // when
        ApplyDecisionResponse receivedResponse = request.send();

        // then
        assertNotNull(receivedResponse);
    }

    @Test
    public void testThrowsExceptionWhenReceivedServerError() throws Exception {
        // given
        MockWebServer server = new MockWebServer();
        setUpMockResponseWithCode(server, HTTP_BAD_GATEWAY);
        ApplyDecisionRequest request = createRequest(server, "a_user_id");

        // when
        try {
            request.send();
            fail("request.send() is expected to throw SiftException");
        } catch (SiftException siftException) {

            // then
            assertEquals("Unexpected API error " + HTTP_BAD_GATEWAY + ".",
                siftException.getMessage());
        }
    }

    private ApplyDecisionRequest createRequest(MockWebServer server, String userId) {
        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());
        client.enqueueRequests();

        // Build and execute the request against the mock server.
        return client.buildRequest(
            new ApplyDecisionFieldSet()
                .setUserId(userId)
                .setTime(System.currentTimeMillis()));
    }

    private void setUpMockResponseWithCode(MockWebServer server, int httpCode) throws IOException {
        MockResponse response = new MockResponse();
        response.setResponseCode(httpCode);

        server.enqueue(response);
        server.start();
    }
}
