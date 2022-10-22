package com.siftscience;

import com.siftscience.model.GetMerchantFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class GetMerchantTest {
    
    @Test
    public void testGetMerchant() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";
        String responseBody = "{\n" +
                "  \"id\": \"api-key-1\",\n" +
                "  \"name\": \"Wonderful Payments Inc.\",\n" +
                "  \"created_at\": 1663162925457,\n" +
                "  \"created_by\": \"d669888796f2a2ac\",\n" +
                "  \"last_updated_at\": 1663162925457,\n" +
                "  \"last_updated_by\": \"d669888796f2a2ac\",\n" +
                "  \"description\": \"Wonderful Payments payment provider.\",\n" +
                "  \"address\": {\n" +
                "    \"name\": \"Alany\",\n" +
                "    \"address_1\": \"Big Payment blvd, 22\",\n" +
                "    \"address_2\": \"apt, 8\",\n" +
                "    \"city\": \"New Orleans\",\n" +
                "    \"region\": \"NA\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"zipcode\": \"76830\",\n" +
                "    \"phone\": \"0394888320\"\n" +
                "  },\n" +
                "  \"category\": \"1002\",\n" +
                "  \"service_level\": \"Platinum\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"risk_profile\": {\n" +
                "    \"level\": \"low\",\n" +
                "    \"score\": 10\n" +
                "  }\n" +
                "}";


        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);

        response.setBody(responseBody);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        GetMerchantRequest getMerchantRequest = client.buildRequest(new GetMerchantFieldSet()
                .setMerchantId("123")
        );

        GetMerchantResponse siftResponse = getMerchantRequest.send();

        // Verify the request.
        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/v3/accounts/" + accountId + "/psp_management/merchants/123", request.getPath());
        Assert.assertEquals(request.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");


        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
    }

}
