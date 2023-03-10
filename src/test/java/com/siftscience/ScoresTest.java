package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.ScoreFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class ScoresTest {
    @Test
    public void testSynchronousScores() throws Exception {
        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\"\n" +
                "}";

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        String bodyStr = "{\n" +
                "    \"status\" : 0,\n" +
                "    \"error_message\" : \"OK\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\",\n" +
                "    \"score_response\": {\n" +
                "      \"status\": 0, \n" +
                "      \"error_message\": \"OK\", \n" +
                "      \"user_id\": \"billy_jones_301\",\n" +
                "      \"scores\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"score\": 0.898391231245,\n" +
                "          \"reasons\": [\n" +
                "            {\n" +
                "              \"name\": \"UsersPerDevice\",\n" +
                "              \"value\": 4,\n" +
                "              \"details\": {\n" +
                "                \"users\": \"a, b, c, d\"\n" +
                "              }\n" +
                "            }\n" +
                "          ],\n" +
                "          \"percentiles\": {\n"+
                "            \"last_7_days\": -1.0,\n"+
                "            \"last_1_days\": -1.0,\n"+
                "            \"last_10_days\": 0.019955654101995565,\n"+
                "            \"last_5_days\": -1.0\n"+
                "           }\n"+
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"score\": 0.472838192111,\n" +
                "          \"reasons\": []\n" +
                "        }\n" +
                "      },\n" +
                "      \"latest_labels\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"is_bad\": true,\n" +
                "          \"time\": 1352201880,\n" +
                "          \"description\": \"received a chargeback\"\n" +
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"is_bad\": false,\n" +
                "          \"time\": 1362205000\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "}";
        response.setBody(bodyStr);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(
                new CreateOrderFieldSet().setUserId("billy_jones_301"))
            .withScores("payment_abuse", "promotion_abuse")
            .withScorePercentiles();
        EventResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events?return_score=true" +
            "&fields=score_percentiles" +
            "&abuse_types=payment_abuse,promotion_abuse", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), false);
        Assert.assertEquals((Double) 0.898391231245,
            siftResponse.getAbuseScore("payment_abuse").getScore());
        Assert.assertEquals(expectedPercentiles(),
            siftResponse.getAbuseScore("payment_abuse").getPercentiles());

        server.shutdown();
    }

    @NotNull
    private static Map<String, Double> expectedPercentiles() {
        Map<String, Double> percentiles = new HashMap<>();
        percentiles.put("last_7_days", -1.0);
        percentiles.put("last_1_days", -1.0);
        percentiles.put("last_10_days", 0.019955654101995565);
        percentiles.put("last_5_days", -1.0);
        return percentiles;
    }

    @Test
    public void testSynchronousScoresWithNoAbuseTypes() throws Exception {
        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\"\n" +
                "}";

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        String bodyStr = "{\n" +
                "    \"status\" : 0,\n" +
                "    \"error_message\" : \"OK\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\",\n" +
                "    \"score_response\": {\n" +
                "      \"status\": 0, \n" +
                "      \"error_message\": \"OK\", \n" +
                "      \"user_id\": \"billy_jones_301\",\n" +
                "      \"scores\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"score\": 0.898391231245,\n" +
                "          \"reasons\": [\n" +
                "            {\n" +
                "              \"name\": \"UsersPerDevice\",\n" +
                "              \"value\": 4,\n" +
                "              \"details\": {\n" +
                "                \"users\": \"a, b, c, d\"\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"score\": 0.472838192111,\n" +
                "          \"reasons\": []\n" +
                "        }\n" +
                "      },\n" +
                "      \"latest_labels\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"is_bad\": true,\n" +
                "          \"time\": 1352201880,\n" +
                "          \"description\": \"received a chargeback\"\n" +
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"is_bad\": false,\n" +
                "          \"time\": 1362205000\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "}";
        response.setBody(bodyStr);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(
                new CreateOrderFieldSet().setUserId("billy_jones_301")).withScores();
        EventResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events?return_score=true", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), false);
        Assert.assertEquals(siftResponse.getAbuseScore("payment_abuse").getScore(),
                (Double) 0.898391231245);

        server.shutdown();
    }

    @Test
    public void testScoresAPI() throws IOException, InterruptedException, JSONException {
        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        String bodyStr = "{\n" +
                "      \"status\": 0, \n" +
                "      \"error_message\": \"OK\", \n" +
                "      \"user_id\": \"billy_jones_301\",\n" +
                "      \"scores\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"score\": 0.898391231245,\n" +
                "          \"reasons\": [\n" +
                "            {\n" +
                "              \"name\": \"UsersPerDevice\",\n" +
                "              \"value\": 4,\n" +
                "              \"details\": {\n" +
                "                \"users\": \"a, b, c, d\"\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"score\": 0.472838192111,\n" +
                "          \"reasons\": []\n" +
                "        }\n" +
                "      },\n" +
                "      \"latest_labels\": {\n" +
                "        \"payment_abuse\": {\n" +
                "          \"is_bad\": true,\n" +
                "          \"time\": 1352201880,\n" +
                "          \"description\": \"received a chargeback\"\n" +
                "        },\n" +
                "        \"promotion_abuse\": {\n" +
                "          \"is_bad\": false,\n" +
                "          \"time\": 1362205000\n" +
                "        }\n" +
                "      }\n" +
                "}";
        response.setBody(bodyStr);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        List<String> abuseTypes = new ArrayList<>();
        abuseTypes.add("payment_abuse");
        abuseTypes.add("promotion_abuse");
        ScoreRequest request = client.buildRequest(
                new ScoreFieldSet()
                        .setUserId("billy_jones_301")
                        .setAbuseTypes(abuseTypes));
        ScoreResponse siftResponse = request.send();


        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("GET", request1.getMethod());
        Assert.assertEquals("/v205/score/billy_jones_301?api_key=YOUR_API_KEY&" +
                "abuse_types=payment_abuse,promotion_abuse", request1.getPath());

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), false);

        server.shutdown();

    }
}
