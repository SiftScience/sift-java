package com.siftscience;

import com.siftscience.model.Address;
import com.siftscience.model.UpdateContentFieldSet;
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

public class UpdateContentEventTest {
    @Test
    public void testUpdateContent() throws Exception {
        String expectedRequestBody = "{\n" +
                "  \"$type\"       : \"$update_content\",\n" +
                "  \"$api_key\"    : \"YOUR_API_KEY_HERE\",\n" +
                "  \"$user_id\"    : \"billy_jones_301\",\n" +
                "\n" +
                "  \"$contact_email\"    : \"bill@example.com\",\n" +
                "  \"$contact_phone\"    : \"1-415-555-6040\",\n" +
                "  \"$content_id\"       : \"9671500641\",\n" +
                "  \"$subject\"          : \"2 Bedroom Apartment for Rent\",\n" +
                "  \"$content\"          : \"Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.\",\n" +
                "  \"$amount\"           : 2300000000,\n" +
                "  \"$currency_code\"    : \"USD\",\n" +
                "  \"$categories\"       : [\n" +
                "    \"Housing\",\n" +
                "    \"Apartments\",\n" +
                "    \"2 Bedrooms\"\n" +
                "  ],\n" +
                "  \"$locations\"        : [\n" +
                "    {\n" +
                "      \"$city\"       : \"Seattle\",\n" +
                "      \"$region\"     : \"Washington\",\n" +
                "      \"$country\"    : \"US\",\n" +
                "      \"$zipcode\"    : \"98112\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"$image_hashes\"     : [\n" +
                "    \"912ec803b2ce49e4a541068d495ab570\",\n" +
                "    \"4be4b314caafaa3e12bfcb8d16df3aff\"\n" +
                "  ],\n" +
                "  \"$expiration_time\"  : 1471003200000,\n" +
                "  \"$status\"           : \"$active\"\n" +
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
        SiftClient client = new SiftClient("YOUR_API_KEY_HERE");
        client.setBaseUrl(baseUrl);

        // Locations
        List<Address> addressList = new ArrayList<>();
        addressList.add(TestUtils.sampleAddress3());

        // Image hashes.
        List<String> imageHashes = new ArrayList<>();
        imageHashes.add("912ec803b2ce49e4a541068d495ab570");
        imageHashes.add("4be4b314caafaa3e12bfcb8d16df3aff");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateContentFieldSet()
                .setUserId("billy_jones_301")
                .setContactEmail("bill@example.com")
                .setContactPhone("1-415-555-6040")
                .setContentId("9671500641")
                .setSubject("2 Bedroom Apartment for Rent")
                .setContent("Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.")
                .setAmount(2300000000L)
                .setCurrencyCode("USD")
                .setCategories(TestUtils.sampleCategories())
                .setLocations(addressList)
                .setImageHashes(imageHashes)
                .setExpirationTime(1471003200000L)
                .setStatus("$active"));

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
