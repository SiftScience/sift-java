package com.siftscience;

import com.siftscience.model.AddItemToCartFieldSet;
import com.siftscience.model.EventResponseBody;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class AddItemToCartEventTest {

    @Test
    public void testAddItemToCart() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$add_item_to_cart\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
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
            "  },\n" +
            "  \"$user_email\" : \"billy_jones_301@email.com\",\n" +
            "  \"$verification_phone_number\" : \"+12345678901\"\n" +
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
        SiftRequest<EventResponse> request = client.buildRequest(new AddItemToCartFieldSet()
            .setUserId("billy_jones_301")
            .setSessionId("gigtleqddo84l8cm15qe4il")
            .setItem(TestUtils.sampleItem2())
            .setUserEmail("billy_jones_301@email.com")
            .setVerificationPhoneNumber("+12345678901"));

        SiftResponse<EventResponseBody> siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertNotNull(siftResponse.getBody());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
            siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
