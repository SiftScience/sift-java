package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.TransactionFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TransactionEventTest {
    @Test
    public void testTransactionEvent() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$transaction\",\n" +
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$amount\"           : 506790000,\n" +
                "  \"$currency_code\"    : \"USD\",\n" +
                "\n" +
                "  \"$user_email\"       : \"bill@gmail.com\",\n" +
                "  \"$transaction_type\" : \"$sale\",\n" +
                "  \"$transaction_status\" : \"$failure\",\n" +
                "  \"$order_id\"         : \"ORDER-123124124\",\n" +
                "  \"$transaction_id\"   : \"719637215\",\n" +
                "  \"$decline_category\" : \"$lost\",\n" +
                "  \"$site_country\": \"US\",\n" +
                "  \"$site_domain\": \"sift.com\",\n" +
                "  \"$brand_name\": \"sift\",\n" +
                "  \"$ordered_from\" : {\n" +
                "    \"$store_id\"      : \"123\",\n" +
                "    \"$store_address\" : {\n" +
                "      \"$address_1\" : \"2100 Main Street\",\n" +
                "      \"$address_2\" : \"Apt 3B\",\n" +
                "      \"$city\"      : \"New London\",\n" +
                "      \"$country\"   : \"US\",\n" +
                "      \"$name\"      : \"Bill Jones\",\n" +
                "      \"$phone\"     : \"1-415-555-6040\",\n" +
                "      \"$region\"    : \"New Hampshire\",\n" +
                "      \"$zipcode\"   : \"03257\"\n" +
                "    }\n" +
                "  },\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(new TransactionFieldSet()
                .setUserId("billy_jones_301")
                .setAmount(506790000L)
                .setCurrencyCode("USD")
                .setUserEmail("bill@gmail.com")
                .setTransactionType("$sale")
                .setTransactionStatus("$failure")
                .setDeclineCategory("$lost")
                .setSiteCountry("US")
                .setSiteDomain("sift.com")
                .setBrandName("sift")
                .setOrderId("ORDER-123124124")
                .setTransactionId("719637215")
                .setBillingAddress(TestUtils.sampleAddress2())
                .setPaymentMethod(TestUtils.samplePaymentMethod1())
                .setShippingAddress(TestUtils.sampleAddress2())
                .setOrderedFrom(TestUtils.sampleOrderedFrom())
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setSellerUserId("slinkys_emporium")
                .setCustomField("digital_wallet", "apple_pay")
                .setCustomField("coupon_code", "dollarMadness")
                .setCustomField("shipping_choice", "FedEx Ground Courier")
                .setCustomField("is_first_time_buyer", false));
        EventResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);
        server.shutdown();
    }

    @Test
    public void testTransactionEventWithWirePaymentMethod() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$transaction\",\n" +
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
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
                "  \"$payment_method\" : {\n" +
                "      \"$payment_type\"         : \"$wire_credit\",\n" +
                "      \"$account_holder_name\"  : \"John Doe\",\n" +
                "      \"$account_number_last5\" : \"12345\",\n" +
                "      \"$bank_name\"            : \"Chase\",\n" +
                "      \"$bank_country\"         : \"US\",\n" +
                "      \"$bic\"                  : \"CHASUS88\",\n" +
                "  }\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(new TransactionFieldSet()
                .setUserId("billy_jones_301")
                .setAmount(506790000L)
                .setCurrencyCode("USD")
                .setUserEmail("bill@gmail.com")
                .setTransactionType("$sale")
                .setTransactionStatus("$success")
                .setOrderId("ORDER-123124124")
                .setTransactionId("719637215")
                .setPaymentMethod(TestUtils.samplePaymentMethodWire()));
        EventResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
