package com.siftscience;

import com.siftscience.model.*;
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
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class UpdateOrderEventTest {

    @Test
    public void testUpdateOrderEvent() throws JSONException, IOException, InterruptedException {

        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$update_order\",\n" +
                "  \"$api_key\"          : \"your_api_key_here\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "\n" +
                "  \"$session_id\"       : \"gigtleqddo84l8cm15qe4il\",\n" +
                "  \"$order_id\"         : \"ORDER-28168441\",\n" +
                "  \"$user_email\"       : \"bill@gmail.com\",\n" +
                "  \"$amount\"           : 115940000,\n" +
                "  \"$currency_code\"    : \"USD\",\n" +
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
                "  \"$payment_methods\"  : [\n" +
                "      {\n" +
                "          \"$payment_type\"    : \"$credit_card\",\n" +
                "          \"$payment_gateway\" : \"$braintree\",\n" +
                "          \"$card_bin\"        : \"542486\",\n" +
                "          \"$card_last4\"      : \"4444\"\n" +
                "      }\n" +
                "  ],\n" +
                "  \"$shipping_address\"  : {\n" +
                "      \"$name\"          : \"Bill Jones\",\n" +
                "      \"$phone\"         : \"1-415-555-6041\",\n" +
                "      \"$address_1\"     : \"2100 Main Street\",\n" +
                "      \"$address_2\"     : \"Apt 3B\",\n" +
                "      \"$city\"          : \"New London\",\n" +
                "      \"$region\"        : \"New Hampshire\",\n" +
                "      \"$country\"       : \"US\",\n" +
                "      \"$zipcode\"       : \"03257\"\n" +
                "  },\n" +
                "  \"$expedited_shipping\" : true,\n" +
                "  \"$shipping_method\"    : \"$physical\",\n" +
                "  \"$items\"             : [\n" +
                "    {\n" +
                "      \"$item_id\"        : \"12344321\",\n" +
                "      \"$product_title\"  : \"Microwavable Kettle Corn: Original Flavor\",\n" +
                "      \"$price\"          : 4990000,\n" +
                "      \"$upc\"            : \"097564307560\",\n" +
                "      \"$sku\"            : \"03586005\",\n" +
                "      \"$brand\"          : \"Peters Kettle Corn\",\n" +
                "      \"$manufacturer\"   : \"Peters Kettle Corn\",\n" +
                "      \"$category\"       : \"Food and Grocery\",\n" +
                "      \"$tags\"           : [\"Popcorn\", \"Snacks\", \"On Sale\"],\n" +
                "      \"$quantity\"       : 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"$item_id\"        : \"B004834GQO\",\n" +
                "      \"$product_title\"  : \"The Slanket Blanket-Texas Tea\",\n" +
                "      \"$price\"          : 39990000,\n" +
                "      \"$upc\"            : \"67862114510011\",\n" +
                "      \"$sku\"            : \"004834GQ\",\n" +
                "      \"$brand\"          : \"Slanket\",\n" +
                "      \"$manufacturer\"   : \"Slanket\",\n" +
                "      \"$category\"       : \"Blankets & Throws\",\n" +
                "      \"$tags\"           : [\"Awesome\", \"Wintertime specials\"],\n" +
                "      \"$color\"          : \"Texas Tea\",\n" +
                "      \"$quantity\"       : 2\n" +
                "    }\n" +
                "  ],\n" +
                "\n" +
                "  \"$seller_user_id\"     : \"slinkys_emporium\",\n" +
                "\n" +
                "  \"$promotions\"         : [\n" +
                "    {\n" +
                "      \"$promotion_id\" : \"FirstTimeBuyer\",\n" +
                "      \"$status\"       : \"$success\",\n" +
                "      \"$description\"  : \"$5 off\",\n" +
                "      \"$discount\"     : {\n" +
                "        \"$amount\"                   : 5000000,\n" +
                "        \"$currency_code\"            : \"USD\",\n" +
                "        \"$minimum_purchase_amount\"  : 25000000\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
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

        // Build the request body.
        // Payment methods.
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(TestUtils.samplePaymentMethod1());

        // Items.
        List<Item> itemList = new ArrayList<>();
        itemList.add(TestUtils.sampleItem1());
        itemList.add(TestUtils.sampleItem2());

        // Promotions.
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(TestUtils.samplePromotion1());


        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(
                new UpdateOrderFieldSet()
                        .setUserId("billy_jones_301")
                        .setSessionId("gigtleqddo84l8cm15qe4il")
                        .setOrderId("ORDER-28168441")
                        .setUserEmail("bill@gmail.com")
                        .setAmount(115940000L)
                        .setCurrencyCode("USD")
                        .setBillingAddress(TestUtils.sampleAddress2())
                        .setPaymentMethods(paymentMethodList)
                        .setShippingAddress(TestUtils.sampleAddress2())
                        .setExpeditedShipping(true)
                        .setShippingMethod("$physical")
                        .setItems(itemList)
                        .setSellerUserId("slinkys_emporium")
                        .setPromotions(promotionList)
                        .setCustomField("digital_wallet", "apple_pay")
                        .setCustomField("coupon_code", "dollarMadness")
                        .setCustomField("shipping_choice", "FedEx Ground Courier")
                        .setCustomField("is_first_time_buyer", false));
        SiftResponse siftResponse = request.send();

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
