package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import static com.siftscience.model.ApplyDecisionFieldSet.DecisionSource;
import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

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

        String responseBody = "" +
                "{" +
                    "\"entity\": {" +
                        "\"id\": \"a_user_id\"," +
                        "\"type\": \"user\"" +
                    "}," +
                    "\"decision\": {" +
                        "\"id\": \"looks_ok_account_abuse\"" +
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
        SiftClient client = new SiftClient("YOUR_API_KEY",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

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
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");

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

        String responseBody = "" +
                "{" +
                    "\"entity\": {" +
                        "\"id\": \"an_order_id\"," +
                        "\"type\": \"order\"" +
                    "}," +
                    "\"decision\": {" +
                        "\"id\": \"order_looks_bad_payment_abuse\"" +
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
        SiftClient client = new SiftClient("YOUR_API_KEY",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        ApplyDecisionRequest request = client.buildRequest(
                new ApplyDecisionFieldSet()
                        .setAccountId(accountId)
                        .setUserId(userId)
                        .setOrderId(orderId)
                        .setDecisionId("order_looks_bad_payment_abuse")
                        .setDescription("suspicious billing to ip distance")
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
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }

    @Test
    public void testApplyDecisionToContentEntity() throws Exception {
        String requestBody =
            "{" +
                "\"decision_id\":\"content_looks_bad_content_abuse\"," +
                "\"source\":\"automated_rule\"," +
                "\"time\":1480618362" +
                "}";

        String responseBody = "" +
            "{" +
            "\"entity\": {" +
            "\"id\": \"a_content_id\"," +
            "\"type\": \"content\"" +
            "}," +
            "\"decision\": {" +
            "\"id\": \"content_looks_bad_content_abuse\"" +
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
        String contentId = "a_content_id";

        HttpUrl baseApi3Url = server.url("").newBuilder().build();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        ApplyDecisionRequest request = client.buildRequest(
            new ApplyDecisionFieldSet()
                .setAccountId(accountId)
                .setUserId(userId)
                .setContentId(contentId)
                .setDecisionId("content_looks_bad_content_abuse")
                .setDescription("suspicious text")
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
                .append("/content/")
                .append(contentId)
                .append("/decisions")
                .toString(),
            request1.getPath());
        Assert.assertEquals(request1.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
            siftResponse.getBody().toJson(), true);
    }

}
