package com.siftscience;

import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.siftscience.model.ApplyDecisionFieldSet.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class ApplyDecisionTest {

    @Test
    public void testApplyDecisionToUserEntity() throws Exception {
        String requestBody =
        "{\n" +
            "\"decision_id\": \"looks_ok_account_abuse\",\n" +
            "\"source\": \"manual_review\",\n" +
            "\"analyst\":\"frank@gmail.com\",\n" +
            "\"time\": 1480618362\n" +
        "}";

        String responseBody =
        "{" +
            "\"time\":" + System.currentTimeMillis() + "," +
            "\"request\":" + requestBody + "," +
            "\"status\":" + 0 + "," +
            "\"error_message\":\"OK\"" +
        "}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);

        server.enqueue(response);
        server.start();

        String accountId = "your_account_id";
        String userId = "a_user_id";

        HttpUrl baseApi3Url = server.url("").newBuilder().build();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseApi3Url(baseApi3Url);

        // Build and execute the request against the mock server.
        ApplyDecisionRequest request = client.buildRequest(
                new ApplyDecisionFieldSet()
                        .setAccountId(accountId)
                        .setUserId(userId)
                        .setDecisionId("your_decision_id")
                        .setSource(DecisionSource.MANUAL_REVIEW)
                        .setAnalyst("analyst@biz.com")
                        .setTime(System.currentTimeMillis()));

        ApplyDecisionResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v3/accounts/" + accountId + "/users/" + userId + "/decisions",
                request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }

    @Test
    public void testApplyDecisionToOrderEntity() throws Exception {
        String requestBody =
                "{" +
                    "\"decision_id\":\"looks_ok_account_abuse\"," +
                    "\"source\":\"automated_rule\"," +
                    "\"time\":1480618362" +
                "}";

        String responseBody =
                "{" +
                    "\"time\":" + System.currentTimeMillis() + "," +
                    "\"request\":" + requestBody + "," +
                    "\"status\":" + 0 + "," +
                    "\"error_message\":\"OK\"" +
                "}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);

        server.enqueue(response);
        server.start();

        String accountId = "your_account_id";
        String userId = "a_user_id";
        String orderId = "an_order_id";

        HttpUrl baseApi3Url = server.url("").newBuilder().build();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseApi3Url(baseApi3Url);

        // Build and execute the request against the mock server.
        ApplyDecisionRequest request = client.buildRequest(
                new ApplyDecisionFieldSet()
                        .setAccountId(accountId)
                        .setUserId(userId)
                        .setOrderId(orderId)
                        .setDecisionId("your_decision_id")
                        .setSource(DecisionSource.AUTOMATED_RULE)
                        .setTime(System.currentTimeMillis()));

        ApplyDecisionResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals(new StringBuilder("/v3/accounts/")
                .append(accountId)
                .append("/users/")
                .append(userId)
                .append("/orders/")
                .append(orderId)
                .append("/decisions")
                .toString(),
                request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic eW91cl9hcGlfa2V5Og==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }

}
