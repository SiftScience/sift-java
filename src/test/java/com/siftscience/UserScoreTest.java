package com.siftscience;

import com.siftscience.model.Decision;
import com.siftscience.model.UserScoreFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserScoreTest {

    private static final String MOCK_RESPONSE = "{\n" +
            "      \"status\": 0, \n" +
            "      \"error_message\": \"OK\", \n" +
            "      \"entity_type\": \"user\",\n" +
            "      \"entity_id\": \"billy_jones_301\",\n" +
            "      \"scores\": {\n" +
            "        \"payment_abuse\": {\n" +
            "          \"status\": 0,\n" +
            "          \"error_message\": \"OK\",\n" +
            "          \"time\": 1352209880,\n" +
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
            "          \"status\": 0,\n" +
            "          \"error_message\": \"OK\",\n" +
            "          \"time\": 1352209880,\n" +
            "          \"score\": 0.472838192111,\n" +
            "          \"reasons\": []\n" +
            "        }\n" +
            "      },\n" +
            "      \"latest_decisions\": {\n" +
            "        \"payment_abuse\": {\n" +
            "          \"id\": \"user_looks_bad_payment_abuse\",\n" +
            "          \"time\": 1352201881,\n" +
            "          \"category\": \"block\",\n" +
            "          \"source\": \"AUTOMATED_RULE\",\n" +
            "          \"description\": \"Bad Fraudster\"\n" +
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

    @Test
    public void testGetUserScoreAPI() throws IOException, InterruptedException,
            JSONException {

        UserScoreFieldSet userScoreFieldSet = new UserScoreFieldSet()
                .setUserId("billy_jones_301")
                .setRescoreUser(false);

        testUserScore(userScoreFieldSet,
                "/v205/users/billy_jones_301/score?api_key=your_api_key_here");
    }

    @Test
    public void testGetUserScoreAPIWithAbuseTypes() throws IOException, InterruptedException,
            JSONException {

        List<String> abuseTypes = new ArrayList<>();
        abuseTypes.add("payment_abuse");
        abuseTypes.add("promotion_abuse");
        UserScoreFieldSet userScoreFieldSet = new UserScoreFieldSet()
                        .setUserId("billy_jones_301")
                        .setAbuseTypes(abuseTypes)
                        .setRescoreUser(false);

        testUserScore(userScoreFieldSet,
                "/v205/users/billy_jones_301/score?api_key=your_api_key_here&" +
                "abuse_types=payment_abuse,promotion_abuse");
    }

    @Test
    public void testRescoreUserScoreAPI() throws IOException, InterruptedException,
            JSONException {

        UserScoreFieldSet userScoreFieldSet = new UserScoreFieldSet()
                .setUserId("billy_jones_301")
                .setRescoreUser(true);

        testUserScore(userScoreFieldSet,
                "/v205/users/billy_jones_301/score?api_key=your_api_key_here");
    }

    @Test
    public void testRescoreUserScoreAPIWithAbuseTypes() throws IOException, InterruptedException,
            JSONException {

        List<String> abuseTypes = new ArrayList<>();
        abuseTypes.add("payment_abuse");
        abuseTypes.add("promotion_abuse");
        UserScoreFieldSet userScoreFieldSet = new UserScoreFieldSet()
                .setUserId("billy_jones_301")
                .setAbuseTypes(abuseTypes)
                .setRescoreUser(true);

        testUserScore(userScoreFieldSet,
                "/v205/users/billy_jones_301/score?api_key=your_api_key_here&" +
                        "abuse_types=payment_abuse,promotion_abuse");
    }

    /**
     * Tests one case of the UserScoreRequest, as dictated by the given field set.
     */
    private static void testUserScore(UserScoreFieldSet userScoreFieldSet,
                                      String expectedUrl) throws IOException, InterruptedException,
            JSONException {

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(MOCK_RESPONSE);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key_here");
        client.setBaseUrl(baseUrl);

        UserScoreRequest request = client.buildRequest(userScoreFieldSet);
        EntityScoreResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals(userScoreFieldSet.getRescoreUser() ? "POST" : "GET",
                request1.getMethod());
        Assert.assertEquals(expectedUrl, request1.getPath());

        // Verify the response.
        Map<String, Decision> expectedDecisions = new HashMap<>();
        Decision expectedPaymentDecision = new Decision()
                .setId("user_looks_bad_payment_abuse")
                .setTime(1352201881)
                .setCategory("block")
                .setSource("AUTOMATED_RULE")
                .setDescription("Bad Fraudster");
        expectedDecisions.put("payment_abuse", expectedPaymentDecision);

        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        Assert.assertEquals(expectedDecisions, siftResponse.getBody().getLatestDecisions());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
