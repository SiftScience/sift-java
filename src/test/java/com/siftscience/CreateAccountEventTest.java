package com.siftscience;

import com.siftscience.model.CreateAccountFieldSet;
import com.siftscience.model.PaymentMethod;
import com.siftscience.model.Promotion;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class CreateAccountEventTest {
    @Test
    public void testCreateAccount() throws Exception {
        String expectedRequestBody = "\n" +
                "{\n" +
                "  \"$type\"       : \"$create_account\",\n" +
                "  \"$api_key\"    : \"your_api_key_here\",\n" +
                "  \"$user_id\"    : \"billy_jones_301\",\n" +
                "\n" +
                "  \"$session_id\"       : \"gigtleqddo84l8cm15qe4il\",\n" +
                "  \"$user_email\"       : \"bill@gmail.com\",\n" +
                "  \"$name\"             : \"Bill Jones\",\n" +
                "  \"$phone\"            : \"1-415-555-6040\",\n" +
                "  \"$referrer_user_id\" : \"janejane101\",\n" +
                "  \"$payment_methods\"  : [\n" +
                "      {\n" +
                "          \"$payment_type\"    : \"$credit_card\",\n" +
                "          \"$card_bin\"        : \"542486\",\n" +
                "          \"$card_last4\"      : \"4444\"\n" +
                "      }\n" +
                "  ],\n" +
                "  \"$billing_address\"  : {\n" +
                "      \"$name\"         : \"Bill Jones\",\n" +
                "      \"$phone\"        : \"1-415-555-6040\",\n" +
                "      \"$address_1\"    : \"2100 Main Street\",\n" +
                "      \"$address_2\"    : \"Apt 3B\",\n" +
                "      \"$city\"         : \"New London\",\n" +
                "      \"$region\"       : \"New Hampshire\",\n" +
                "      \"$country\"      : \"US\",\n" +
                "      \"$zipcode\"      : \"03257\"\n" +
                "  },\n" +
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
                "  \"$promotions\"       : [\n" +
                "    {\n" +
                "      \"$promotion_id\"     : \"FriendReferral\",\n" +
                "      \"$status\"           : \"$success\",\n" +
                "      \"$referrer_user_id\" : \"janejane102\",\n" +
                "      \"$credit_point\"     : {\n" +
                "        \"$amount\"             : 100,\n" +
                "        \"$credit_point_type\"  : \"account karma\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "\n" +
                "  \"$social_sign_on_type\"   : \"$twitter\",\n" +
                "\n" +
                "  \"twitter_handle\"          : \"billyjones\",\n" +
                "  \"work_phone\"              : \"1-347-555-5921\",\n" +
                "  \"location\"                : \"New London, NH\",\n" +
                "  \"referral_code\"           : \"MIKEFRIENDS\",\n" +
                "  \"email_confirmed_status\"  : \"$pending\",\n" +
                "  \"phone_confirmed_status\"  : \"$pending\"\n" +
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

        // Payment methods.
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(TestUtils.samplePaymentMethod2());

        // Promotions.
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(TestUtils.samplePromotion2());

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(
                new CreateAccountFieldSet()
                        .setUserId("billy_jones_301")
                        .setSessionId("gigtleqddo84l8cm15qe4il")
                        .setUserEmail("bill@gmail.com")
                        .setName("Bill Jones")
                        .setPhone("1-415-555-6040")
                        .setReferrerUserId("janejane101")
                        .setPaymentMethods(paymentMethodList)
                        .setBillingAddress(TestUtils.sampleAddress1())
                        .setShippingAddress(TestUtils.sampleAddress2())
                        .setPromotions(promotionList)
                        .setSocialSignOnType("$twitter")
                        .setCustomField("twitter_handle", "billyjones")
                        .setCustomField("work_phone", "1-347-555-5921")
                        .setCustomField("location", "New London, NH")
                        .setCustomField("referral_code", "MIKEFRIENDS")
                        .setCustomField("email_confirmed_status", "$pending")
                        .setCustomField("phone_confirmed_status", "$pending"));
        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v204/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        server.shutdown();
    }
}
