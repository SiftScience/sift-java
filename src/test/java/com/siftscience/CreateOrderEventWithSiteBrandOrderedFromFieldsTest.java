package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.PaymentMethod;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class CreateOrderEventWithSiteBrandOrderedFromFieldsTest {

    @Test
    public void testCreateOrderEvent() throws JSONException, IOException,
        InterruptedException {

        // The expected JSON payload of the request.
        String expectedRequestBody = "{\n" +
            "  \"$type\": \"$create_order\",\n" +
            "  \"$api_key\": \"YOUR_API_KEY\",\n" +
            "  \"$user_id\": \"billy_jones_301\",\n" +
            "  \"$session_id\": \"gigtleqddo84l8cm15qe4il\",\n" +
            "  \"$order_id\": \"ORDER-28168441\",\n" +
            "  \"$user_email\": \"bill@gmail.com\",\n" +
            "  \"$amount\": 115940000,\n" +
            "  \"$currency_code\": \"USD\",\n" +
            "  \"$site_country\": \"US\",\n" +
            "  \"$site_domain\": \"sift.com\",\n" +
            "  \"$brand_name\": \"sift\",\n" +
            "  \"$payment_methods\": [\n" +
            "    {\n" +
            "      \"$payment_type\": \"$credit_card\",\n" +
            "      \"$payment_gateway\": \"$braintree\",\n" +
            "      \"$card_bin\": \"542486\",\n" +
            "      \"$card_last4\": \"4444\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"$billing_address\": {\n" +
            "    \"$name\": \"Bill Jones\",\n" +
            "    \"$phone\": \"1-415-555-6041\",\n" +
            "    \"$address_1\": \"2100 Main Street\",\n" +
            "    \"$address_2\": \"Apt 3B\",\n" +
            "    \"$city\": \"New London\",\n" +
            "    \"$region\": \"New Hampshire\",\n" +
            "    \"$country\": \"US\",\n" +
            "    \"$zipcode\": \"03257\"\n" +
            "  },\n" +
            "  \"$shipping_address\": {\n" +
            "    \"$name\": \"Bill Jones\",\n" +
            "    \"$phone\": \"1-415-555-6041\",\n" +
            "    \"$address_1\": \"2100 Main Street\",\n" +
            "    \"$address_2\": \"Apt 3B\",\n" +
            "    \"$city\": \"New London\",\n" +
            "    \"$region\": \"New Hampshire\",\n" +
            "    \"$country\": \"US\",\n" +
            "    \"$zipcode\": \"03257\"\n" +
            "  },\n" +
            "  \"$expedited_shipping\": true,\n" +
            "  \"$shipping_method\": \"$physical\",\n" +
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
            "  }\n" +
            "}\n";

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

        // Payment methods.
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(TestUtils.samplePaymentMethod1());

        // Build and execute the request against the mock server.
        EventRequest request = client.buildRequest(
            new CreateOrderFieldSet()
                .setUserId("billy_jones_301")
                .setSessionId("gigtleqddo84l8cm15qe4il")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com")
                .setAmount(115940000L)
                .setCurrencyCode("USD")
                .setBillingAddress(TestUtils.sampleAddress2())
                .setOrderedFrom(TestUtils.sampleOrderedFrom())
                .setSiteDomain("sift.com")
                .setSiteCountry("US")
                .setBrandName("sift")
                .setPaymentMethods(paymentMethodList)
                .setShippingAddress(TestUtils.sampleAddress2())
                .setExpeditedShipping(true)
                .setShippingMethod("$physical"));

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
