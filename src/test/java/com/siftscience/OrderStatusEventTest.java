package com.siftscience;

import com.siftscience.model.OrderStatusFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class OrderStatusEventTest {
    @Test
    public void testOrderStatus() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$order_status\",\n" +
                "  \"$api_key\"          : \"your_api_key_here\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$order_id\"         : \"ORDER-28168441\",\n" +
                "  \"$order_status\"     : \"$canceled\",\n" +
                "\n" +
                "  \"$reason\"     : \"$payment_risk\",\n" +
                "  \"$source\"     : \"$manual_review\",\n" +
                "  \"$analyst\"    : \"someone@your-site.com\",\n" +
                "  \"$webhook_id\" : \"3ff1082a4aea8d0c58e3643ddb7a5bb87ffffeb2492dca33\",\n" +
                "  \"$description\": \"Canceling because multiple fraudulent users on device\"\n" +
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
        SiftRequest request = client.buildEventRequest(new OrderStatusFieldSet()
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setOrderStatus("$canceled")
                .setReason("$payment_risk")
                .setSource("$manual_review")
                .setAnalyst("someone@your-site.com")
                .setWebhookId("3ff1082a4aea8d0c58e3643ddb7a5bb87ffffeb2492dca33")
                .setDescription("Canceling because multiple fraudulent users on device"));

        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v204/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getResponseBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getResponseBody().toJson(), true);

        server.shutdown();
    }
}
