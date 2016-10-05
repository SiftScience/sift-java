package com.siftscience;

import com.siftscience.SiftClient;
import com.siftscience.SiftRequest;
import com.siftscience.model.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderFieldsTest {
    @Test
    public void testCreateOrderFieldsSerDe() throws JSONException, IOException,
            InterruptedException {

        // Start a new mock server.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_OK);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key_here");
        client.setBaseUrl(baseUrl);

        // Build the request body.

        // Payment methods.
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod()
                .setPaymentType("$credit_card")
                .setPaymentGateway("$braintree")
                .setCardBin("542486")
                .setCardLast4("4444"));

        // Items.
        List<Item> itemList = new ArrayList<>();

        // Tags for the first item.
        List<String> tags1 = new ArrayList<>();
        tags1.add("Popcorn");
        tags1.add("Snacks");
        tags1.add("On Sale");

        // First item.
        itemList.add(new Item()
                .setItemId("12344321")
                .setProductTitle("Microwavable Kettle Corn: Original Flavor")
                .setPrice(4990000L)
                .setUpc("097564307560")
                .setSku("03586005")
                .setBrand("Peters Kettle Corn")
                .setManufacturer("Peters Kettle Corn")
                .setCategory("Food and Grocery")
                .setTags(tags1)
                .setQuantity(4L));

        // Tags for the second item.
        List<String> tags2 = new ArrayList<>();
        tags2.add("Awesome");
        tags2.add("Wintertime specials");

        // Second item.
        itemList.add(new Item()
                .setItemId("B004834GQO")
                .setProductTitle("The Slanket Blanket-Texas Tea")
                .setPrice(39990000L)
                .setUpc("67862114510011")
                .setSku("004834GQ")
                .setBrand("Slanket")
                .setManufacturer("Slanket")
                .setCategory("Blankets & Throws")
                .setTags(tags2)
                .setColor("Texas Tea")
                .setQuantity(2L));

        // Promotions.
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(new Promotion()
                .setPromotionId("FirstTimeBuyer")
                .setStatus("$success")
                .setDescription("$5 off")
                .setDiscount(new Discount()
                        .setAmount(5000000L)
                        .setCurrencyCode("USD")
                        .setMinimumPurchaseAmount(25000000L)));

        // Main event fields object.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("your_api_key_here")
                .setUserId("billy_jones_301")
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com")
                .setAmount(115940000L)
                .setCurrencyCode("USD")
                .setBillingAddress(new Address()
                        .setName("Bill Jones")
                        .setPhone("1-415-555-6041")
                        .setAddress1("2100 Main Street")
                        .setAddress2("Apt 3B")
                        .setCity("New London")
                        .setRegion("New Hampshire")
                        .setCountry("US")
                        .setZipCode("03257"))
                .setPaymentMethods(paymentMethodList)
                .setShippingAddress(new Address()
                        .setName("Bill Jones")
                        .setPhone("1-415-555-6041")
                        .setAddress1("2100 Main Street")
                        .setAddress2("Apt 3B")
                        .setCity("New London")
                        .setRegion("New Hampshire")
                        .setCountry("US")
                        .setZipCode("03257"))
                .setExpeditedShipping(true)
                .setShippingMethod("$physical")
                .setItems(itemList)
                .setSellerUserId("slinkys_emporium")
                .setPromotions(promotionList)
                .setCustomField("digital_wallet", "apple_pay")
                .setCustomField("coupon_code", "dollarMadness")
                .setCustomField("shipping_choice", "FedEx Ground Courier")
                .setCustomField("is_first_time_buyer", false);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildCreateOrderRequest("billy_jones_301", fields);
        SiftResponse siftResponse = request.send();

        String expected = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
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


        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("/v204/events", request1.getPath());
        JSONAssert.assertEquals(expected, fields.toJson(), true);

        server.shutdown();
    }
}
