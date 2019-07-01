package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.siftscience.model.Booking;
import com.siftscience.model.PaymentMethod;
import com.siftscience.model.Promotion;
import com.siftscience.model.UpdateOrderFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class UpdateOrderEventWithBookingsTest {

    @Test
    public void testUpdateOrderEvent() throws JSONException, IOException, InterruptedException {

        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
            "  \"$type\"             : \"$update_order\",\n" +
            "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
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
            "  \"$bookings\": [\n" +
            "    {\n" +
            "      \"$booking_type\": \"$flight\",\n" +
            "      \"$title\": \"SFO - LAS, 2 Adults\",\n" +
            "      \"$start_time\": 12038412903,\n" +
            "      \"$end_time\": 12048412903,\n" +
            "      \"$guests\": [\n" +
            "        {\n" +
            "          \"$name\": \"John Doe\",\n" +
            "          \"$birth_date\": \"1985-01-19\",\n" +
            "          \"$loyalty_program\": \"skymiles\",\n" +
            "          \"$loyalty_program_id\": \"PSOV34DF\",\n" +
            "          \"$phone\": \"1-415-555-6040\",\n" +
            "          \"$email\": \"jdeo@domain.com\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"$name\": \"Jane Doe\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"$segments\": [\n" +
            "        {\n" +
            "          \"$departure_address\":\n" +
            "          {\n" +
            "            \"$name\": \"Bill Jones\",\n" +
            "            \"$phone\": \"1-415-555-6040\",\n" +
            "            \"$address_1\": \"2100 Main Street\",\n" +
            "            \"$address_2\": \"Apt 3B\",\n" +
            "            \"$city\": \"New London\",\n" +
            "            \"$region\": \"New Hampshire\",\n" +
            "            \"$country\": \"US\",\n" +
            "            \"$zipcode\": \"03257\"\n" +
            "          },\n" +
            "          \"$arrival_address\":\n" +
            "          {\n" +
            "            \"$name\": \"Bill Jones\",\n" +
            "            \"$phone\": \"1-415-555-6041\",\n" +
            "            \"$address_1\": \"2100 Main Street\",\n" +
            "            \"$address_2\": \"Apt 3B\",\n" +
            "            \"$city\": \"New London\",\n" +
            "            \"$region\": \"New Hampshire\",\n" +
            "            \"$country\": \"US\",\n" +
            "            \"$zipcode\": \"03257\"\n" +
            "          },\n" +
            "          \"$start_time\": 2190121220,\n" +
            "          \"$end_time\": 2290122129,\n" +
            "          \"$vessel_number\": \"LH454\",\n" +
            "          \"$fare_class\": \"Premium Economy\",\n" +
            "          \"$departure_airport_code\": \"SFO\",\n" +
            "          \"$arrival_airport_code\": \"LAS\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"$price\": 49900000,\n" +
            "      \"$currency_code\": \"USD\",\n" +
            "      \"$quantity\": 1\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        // Build the request body.
        // Payment methods.
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(TestUtils.samplePaymentMethod1());

        // Bookings
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(TestUtils.sampleBooking());

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
                .setBookings(bookingList)
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
