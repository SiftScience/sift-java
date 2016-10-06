package com.siftscience;

import com.siftscience.model.AddItemToCartFieldSet;
import com.siftscience.model.Item;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class AddItemToCardEventTest {
    @Test
    public void testAddItemToCart() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"       : \"$add_item_to_cart\",\n" +
                "  \"$api_key\"    : \"your_api_key_here\",\n" +
                "  \"$user_id\"    : \"billy_jones_301\",\n" +
                "\n" +
                "  \"$session_id\" : \"gigtleqddo84l8cm15qe4il\",\n" +
                "  \"$item\"       : {\n" +
                "    \"$item_id\"        : \"B004834GQO\",\n" +
                "    \"$product_title\"  : \"The Slanket Blanket-Texas Tea\",\n" +
                "    \"$price\"          : 39990000,\n" +
                "    \"$upc\"            : \"67862114510011\",\n" +
                "    \"$sku\"            : \"004834GQ\",\n" +
                "    \"$brand\"          : \"Slanket\",\n" +
                "    \"$manufacturer\"   : \"Slanket\",\n" +
                "    \"$category\"       : \"Blankets & Throws\",\n" +
                "    \"$tags\"           : [\"Awesome\", \"Wintertime specials\"],\n" +
                "    \"$color\"          : \"Texas Tea\",\n" +
                "    \"$quantity\"       : 2\n" +
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
        SiftRequest request = client.buildEventRequest(new AddItemToCartFieldSet()
                .setUserId("billy_jones_301")
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setItem(TestUtils.sampleItem2()));

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
