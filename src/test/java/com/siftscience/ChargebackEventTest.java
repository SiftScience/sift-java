package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.ChargebackFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class ChargebackEventTest {
    @Test
    public void testChargeback() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"              : \"$chargeback\",\n" +
                "  \"$api_key\"           : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"           : \"billy_jones_301\",\n" +
                "  \"$order_id\"          : \"ORDER-123124124\",\n" +
                "  \"$transaction_id\"    : \"719637215\",\n" +
                "\n" +
                "  \"$chargeback_state\"  : \"$lost\",\n" +
                "  \"$chargeback_reason\" : \"$duplicate\"\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(new ChargebackFieldSet()
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-123124124")
                .setTransactionId("719637215")
                .setChargebackState("$lost")
                .setChargebackReason("$duplicate"));

        EventResponse siftResponse = request.send();

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
