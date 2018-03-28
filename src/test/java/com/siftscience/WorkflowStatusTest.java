package com.siftscience;

import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class WorkflowStatusTest {
    @Test
    public void testSynchronousWorkflowStatus() throws Exception {

        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"your_api_key_here\",\n" +
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
                "      },\n" +

                "       \"workflow_statuses\": [\n" +
                "        {\n" +
                "          \"id\": \"6dbq76qbaaaaa\",\n" +
                "          \"state\": \"running\",\n" +
                "          \"config\": {\n" +
                "            \"id\": \"pv3u5hyaaa\",\n" +
                "            \"version\": \"1468013109122\"\n" +
                "          },\n" +
                "          \"config_display_name\": \"my create order flow\",\n" +
                "          \"abuse_types\": [\"payment_abuse\", \"legacy\"],\n" +
                "          \"entity\": {\n" +
                "            \"type\": \"user\",\n" +
                "            \"id\": \"test\"\n" +
                "          },\n" +
                "          \"history\": [\n" +
                "            {\n" +
                "              \"app\": \"decision\",\n" +
                "              \"name\": \"ban user\",\n" +
                "              \"state\": \"running\",\n" +
                "              \"config\": {\n" +
                "               \"decision_id\": \"ban-user-payment-abuse\"\n" +
                "            }\n" +
                "            },\n" +
                "            {\n" +
                "              \"app\": \"review_queue\",\n" +
                "              \"name\": \"risky user queue\",\n" +
                "              \"state\": \"finished\",\n" +
                "              \"config\": {\n" +
                "                \"buttons\": [\n" +
                "                  { \"id\": \"ban-user-payment-abuse\", \"name\": \"Ban User\" },\n" +
                "                  { \"id\": \"suspend-user-payment-abuse\", \"name\": \"Ban User\" },\n" +
                "                  { \"id\": \"accept-user-payment-abuse\", \"name\": \"Ban User\" }\n" +
                "                ]\n" +
                "              }\n" +
                "            },\n" +
                "            {\n" +
                "              \"app\": \"user_scorer\",\n" +
                "              \"name\": \"Entity\",\n" +
                "              \"state\": \"finished\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"app\": \"event_processor\",\n" +
                "              \"name\": \"Event\",\n" +
                "              \"state\": \"finished\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +

                "    }\n" +
                "}";
        response.setBody(bodyStr);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl;
        baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key_here");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(
                new CreateOrderFieldSet().setUserId("billy_jones_301"))
                        .withScores("payment_abuse", "promotion_abuse").withWorkflowStatus();
        EventResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events?return_workflow_status=true&abuse_types=" +
                "payment_abuse,promotion_abuse", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
        Assert.assertEquals(siftResponse.getAbuseScore("payment_abuse").getScore(),
                (Double) 0.898391231245);
        Assert.assertEquals(siftResponse.getWorkflowStatuses().get(0).getId(), "6dbq76qbaaaaa");
        Assert.assertEquals(siftResponse.getWorkflowStatuses().get(0)
                .getHistory().get(0).getState(), "running");

        server.shutdown();
    }

    @Test
    public void testAsyncWorkflowStatus() throws Exception {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        String rspBody = "{\n" +
                "  \"id\": \"6dbq76qbaaaaa\",\n" +
                "  \"state\": \"running\",\n" +
                "  \"config\": {\n" +
                "    \"id\": \"pv3u5hyaaa\",\n" +
                "    \"version\": \"1468013109122\"\n" +
                "  },\n" +
                "  \"config_display_name\": \"my create order flow\",\n" +
                "  \"abuse_types\": [\"payment_abuse\", \"legacy\"],\n" +
                "  \"entity\": {\n" +
                "    \"type\": \"user\",\n" +
                "    \"id\": \"dracula@vampires.com\"\n" +
                "  },\n" +
                "  \"history\": [\n" +
                "    {\n" +
                "      \"app\": \"decision\",\n" +
                "      \"name\": \"ban user\",\n" +
                "      \"state\": \"running\",\n" +
                "      \"config\": {\n" +
                "         \"decision_id\": \"ban-user-payment-abuse\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"app\": \"review_queue\",\n" +
                "      \"name\": \"risky user queue\",\n" +
                "      \"state\": \"finished\",\n" +
                "      \"config\": {\n" +
                "        \"buttons\": [\n" +
                "          { \"id\": \"ban-user-payment-abuse\", \"name\": \"Ban User\" },\n" +
                "          { \"id\": \"suspend-user-payment-abuse\", \"name\": \"Ban User\" },\n" +
                "          { \"id\": \"accept-user-payment-abuse\", \"name\": \"Ban User\" }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"app\": \"user_scorer\",\n" +
                "      \"name\": \"Entity\",\n" +
                "      \"state\": \"finished\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"app\": \"event_processor\",\n" +
                "      \"name\": \"Event\",\n" +
                "      \"state\": \"finished\"\n" +
                "    }\n" +
                "  ]\n" +
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
        WorkflowStatusRequest request = client.buildRequest(
                new WorkflowStatusFieldSet().setAccountId("your_account_id")
                        .setWorkflowRunId("someid"));
        WorkflowStatusResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("GET", request1.getMethod());
        Assert.assertEquals("/v3/accounts/your_account_id/workflows/runs/someid",
                request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }
}
