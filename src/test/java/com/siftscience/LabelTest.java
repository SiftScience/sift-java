package com.siftscience;

import com.siftscience.model.LabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class LabelTest {
    @Test
    public void testLabel() throws Exception {
        String expectedRequestBody = "{ \n" +
                "  \"$api_key\"     : \"23b87a99k099fc98\", \n" +
                "  \"$is_bad\"      : true, \n" +
                "  \"$abuse_type\"  : \"payment_abuse\",\n" +
                "  \"$description\" :" +
                            " \"The user was testing cards repeatedly for a valid " +
                "card\", \n" +
                "  \"$source\"      : \"manual review\", \n" +
                "  \"$analyst\"     : \"someone@your-site.com\" \n" +
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
        SiftClient client = new SiftClient("23b87a99k099fc98");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        LabelRequest request = client.buildRequest(new LabelFieldSet()
                .setUserId("billy_jones_301")
                .setIsBad(true)
                .setAbuseType("payment_abuse")
                .setDescription("The user was testing cards repeatedly for a valid card")
                .setSource("manual review")
                .setAnalyst("someone@your-site.com"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v204/users/billy_jones_301/labels", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getResponseBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getResponseBody().toJson(), true);

        server.shutdown();
    }
}
