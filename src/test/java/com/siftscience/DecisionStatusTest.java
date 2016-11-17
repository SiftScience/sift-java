package com.siftscience;

import com.siftscience.model.DecisionStatusFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class DecisionStatusTest {
    @Test
    public void testDecisionStatus() throws Exception {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);

        String rspBody = "{\n" +
                "  \"decisions\": {\n" +
                "    \"payment_abuse\": {\n" +
                "      \"decision\": {\n" +
                "        \"id\": \"block_user_payment_abuse\"\n" +
                "      },\n" +
                "      \"time\": 1461963439151,\n" +
                "      \"webhook_succeeded\": true\n" +
                "    },\n" +
                "    \"promo_abuse\": {\n" +
                "      \"decision\": {\n" +
                "        \"id\": \"reject_promo_promo_abuse\"\n" +
                "      },\n" +
                "      \"time\": 1461963431151,\n" +
                "      \"webhook_succeeded\": true\n" +
                "    },\n" +
                "    \"content_abuse\": {\n" +
                "      \"decision\": {\n" +
                "        \"id\": \"block_post_content_abuse\"\n" +
                "      },\n" +
                "      \"time\": 1461963429151,\n" +
                "      \"webhook_succeeded\": true\n" +
                "    },\n" +
                "    \"account_abuse\": {\n" +
                "      \"decision\": {\n" +
                "        \"id\": \"ban_account_account_abuse\"\n" +
                "      },\n" +
                "      \"time\": 1461963839151,\n" +
                "      \"webhook_succeeded\": true\n" +
                "    },\n" +
                "    \"legacy\": {\n" +
                "      \"decision\": {\n" +
                "        \"id\": \"block_user_legacy\"\n" +
                "      },\n" +
                "      \"time\": 1461966439151,\n" +
                "      \"webhook_succeeded\": false\n" +
                "    }\n" +
                "  }\n" +
                "}";

        response.setBody(rspBody);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl;
        baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseApi3Url(baseUrl);

        // Build and execute the request against the mock server.
        DecisionStatusRequest request = client.buildRequest(
                new DecisionStatusFieldSet()
                        .setAccountId("your_account_id")
                        .setEntity(DecisionStatusFieldSet.ENTITY_ORDERS)
                        .setEntityId("someid"));
        DecisionStatusResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("GET", request1.getMethod());
        Assert.assertEquals("/v3/accounts/your_account_id/orders/someid/decisions",
                request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getResponseBody().toJson(), true);
    }
}
