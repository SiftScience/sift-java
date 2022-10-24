package com.siftscience;

import com.siftscience.model.*;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class UpdateMerchantEventTest {

    @Test
    public void testUpdateMerchant() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"id\": \"api-key-1\",\n" +
                "  \"name\": \"Wonderful Payments Inc.\",\n" +
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

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(expectedRequestBody);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());


        // Build and execute the request against the mock server.
        UpdateMerchantRequest request = client.buildRequest(
                new UpdateMerchantFieldSet().setId("api-key-1")
                .setName("Wonderful Payments Inc.")
                .setDescription("Wonderful Payments payment provider.")
                .setAddress(
                    new MerchantAddress().setName("Alany")
                        .setAddress1("Big Payment blvd, 22")
                            .setAddress2("apt, 8")
                        .setCity("New Orleans")
                        .setRegion("NA")
                        .setCountry("US")
                        .setZipCode("76830")
                        .setPhone("0394888320")
                ).setCategory("1002")
                .setServiceLevel("Platinum")
                .setStatus(MerchantStatusEnum.ACTIVE)
                .setRiskProfile(
                        new RiskProfile().setLevel(MerchantRiskLevelEnum.LOW).setScore(10L)
                ), "1234"
        );

        UpdateMerchantResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v3/accounts/YOUR_ACCOUNT_ID/psp_management/merchants/1234", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
       // Assert.assertEquals(0, siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
        server.shutdown();
    }
}
