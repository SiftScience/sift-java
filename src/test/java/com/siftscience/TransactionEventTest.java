package com.siftscience;

import com.siftscience.model.TransactionFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class TransactionEventTest {
    @Test
    public void testTransactionEvent() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$transaction\",\n" +
                "  \"$api_key\"          : \"your_api_key_here\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$amount\"           : 506790000,\n" +
                "  \"$currency_code\"    : \"USD\",\n" +
                "\n" +
                "  \"$user_email\"       : \"bill@gmail.com\",\n" +
                "  \"$transaction_type\" : \"$sale\",\n" +
                "  \"$transaction_status\" : \"$success\",\n" +
                "  \"$order_id\"         : \"ORDER-123124124\",\n" +
                "  \"$transaction_id\"   : \"719637215\",\n" +
                "\n" +
                "  \"$billing_address\"  : {\n" +
                "      \"$name\"         : \"Bill Jones\",\n" +
                "      \"$phone\"        : \"1-415-555-6041\",\n" +
                "      \"$address_1\"    : \"2100 Main Street\",\n" +
                "      \"$address_2\"    : \"Apt 3B\",\n" +
                "      \"$city\"         : \"New London\",\n" +
                "      \"$region\"       : \"New Hampshire\",\n" +
                "      \"$country\"      : \"US\",\n" +
                "      \"$zipcode\"      : \"03257\"\n" +
                "  },\n" +
                "  \"$payment_method\"   : {\n" +
                "      \"$payment_type\"    : \"$credit_card\",\n" +
                "      \"$payment_gateway\" : \"$braintree\",\n" +
                "      \"$card_bin\"        : \"542486\",\n" +
                "      \"$card_last4\"      : \"4444\"\n" +
                "  },\n" +
                "  \"$shipping_address\" : {\n" +
                "      \"$name\"         : \"Bill Jones\",\n" +
                "      \"$phone\"        : \"1-415-555-6041\",\n" +
                "      \"$address_1\"    : \"2100 Main Street\",\n" +
                "      \"$address_2\"    : \"Apt 3B\",\n" +
                "      \"$city\"         : \"New London\",\n" +
                "      \"$region\"       : \"New Hampshire\",\n" +
                "      \"$country\"      : \"US\",\n" +
                "      \"$zipcode\"      : \"03257\"\n" +
                "  },\n" +
                "  \"$session_id\"       : \"gigtleqddo84l8cm15qe4il\",\n" +
                "\n" +
                "  \"$seller_user_id\"     : \"slinkys_emporium\",\n" +
                "\n" +
                "  \"digital_wallet\"      : \"apple_pay\",\n" +
                "  \"coupon_code\"         : \"dollarMadness\",\n" +
                "  \"shipping_choice\"     : \"FedEx Ground Courier\",\n" +
                "  \"is_first_time_buyer\" : false\n" +
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
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key_here");
        client.setBaseUrl(baseUrl);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new TransactionFieldSet()
                .setUserId("billy_jones_301")
                .setAmount(506790000L)
                .setCurrencyCode("USD")
                .setUserEmail("bill@gmail.com")
                .setTransactionType("$sale")
                .setTransactionStatus("$success")
                .setOrderId("ORDER-123124124")
                .setTransactionId("719637215")
                .setBillingAddress(TestUtils.sampleAddress2())
                .setPaymentMethod(TestUtils.samplePaymentMethod1())
                .setShippingAddress(TestUtils.sampleAddress2())
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setSellerUserId("slinkys_emporium")
                .setCustomField("digital_wallet", "apple_pay")
                .setCustomField("coupon_code", "dollarMadness")
                .setCustomField("shipping_choice", "FedEx Ground Courier")
                .setCustomField("is_first_time_buyer", false));
        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v204/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getResponseBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getResponseBody().toJson(), true);

        server.shutdown();
    }
}
