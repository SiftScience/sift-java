package com.siftscience;

import com.siftscience.model.ApplyDecisionFieldSet;
import com.siftscience.model.DecisionLogJson;
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
        String responseBody = "" +
                "{" +
                    "\"entity\": {" +
                        "\"id\": \"a_user_id\"," +
                        "\"type\": \"user\"," +
                        "\"ref\": {" +
                            "\"id\": \"a_user_id\"," +
                            "\"href\": \"path/to/user/a_user_id\"" +
                        "}" +
                    "}," +
                    "\"decision\": {" +
                        "\"id\": \"looks_ok_account_abuse\"," +
                        "\"href\": \"path/to/decision/looks_ok_account_abuse\"" +
                    "}," +
                    "\"source\": {" +
                        "\"type\": \"ANALYST\"," +
                        "\"ref\": {" +
                            "\"id\": \"analyst@biz.com\"," +
                            "\"href\": \"path/to/analyst/frank@biz.com\"" +
                        "}" +
                    "}," +
                    "\"time\": 123213123" +
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
                        .setDecisionId("looks_ok_account_abuse")
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
        String responseBody = "" +
                "{" +
                    "\"entity\": {" +
                        "\"id\": \"an_order_id\"," +
                        "\"type\": \"order\"," +
                        "\"ref\": {" +
                            "\"id\": \"an_order_id\"," +
                            "\"href\": \"path/to/order/an_order_id\"" +
                        "}" +
                    "}," +
                    "\"decision\": {" +
                        "\"id\": \"order_looks_bad_payment_abuse\"," +
                        "\"href\": \"path/to/decision/looks_ok_account_abuse\"" +
                    "}," +
                    "\"source\": {" +
                        "\"type\": \"AUTOMATED_RULE\"" +
                    "}," +
                    "\"time\": 123213123" +
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
                        .setDecisionId("order_looks_bad_payment_abuse")
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
