package com.siftscience;

import com.siftscience.exception.*;
import com.siftscience.model.CreateOrderFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

/**
 * This class includes tests for the basic functionality of the client such as error handling and
 * serialization edge cases as opposed to testing the correctness of specific event and field
 * types according to the API docs.
 */
public class SiftClientTest {

    /**
     * If the server sends a 51 Sift error code then we should throw an InvalidApiKeyException
     * and pass through the error description as the exception message.
     */
    @Test
    public void testInvalidAPIKeyException() throws Exception {

        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"invalid_api_key\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$order_id\"          : \"ORDER-28168441\",\n" +
                "  \"$user_email\"          : \"bill@gmail.com\",\n" +
                "}";

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 51,\n" +
                "    \"error_message\" : \"Invalid API key message.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("invalid_api_key");
        client.setBaseUrl(baseUrl);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("invalid_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildEventRequest(fields);
        InvalidApiKeyException apiKeyException = null;
        try {
            request.send();
        } catch (InvalidApiKeyException e) {
            apiKeyException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(apiKeyException);
        Assert.assertEquals("Invalid API key message.", apiKeyException.getLocalizedMessage());

        // Check that we can access the API key from the exception object.
        Assert.assertEquals("invalid_api_key",
                apiKeyException.getSiftResponse().getRequestBody().getApiKey());

        server.shutdown();
    }

    /**
     * Lets pretend that $user_email is a required field and the client doesn't know the fact.
     * The server will let us know that it's missing and we should throw a MissingFieldException.
     */
    @Test
    public void testServerSideMissingFieldException() throws IOException {

        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"your_api_key\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$order_id\"          : \"ORDER-28168441\"\n" +
                "}";

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 55,\n" +
                "    \"error_message\" : \"Missing user email message from server.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("your_api_key");
        client.setBaseUrl(baseUrl);

        // Build a simplified request body with the "missing field".
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildEventRequest(fields);
        MissingFieldException missingFieldException = null;
        try {
            request.send();
        } catch (MissingFieldException e) {
            missingFieldException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(missingFieldException);
        Assert.assertEquals("Missing user email message from server.",
                missingFieldException.getLocalizedMessage());

        server.shutdown();
    }

    /**
     * Now we test that the library correctly throws a MissingFieldException during the
     * client side validation stage of the request being sent. No mock web server because the
     * request should never be sent.
     */
    @Test
    public void testClientSideMissingFieldException() throws IOException {

        // Create a new client and link it to a fake server address.
        SiftClient client = new SiftClient();
        client.setBaseUrl(HttpUrl.parse("http://fakehost:1234"));

        // Build a simple request. The main part is that we don't manually set an API key here
        // and we also haven't specified it on the client.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setUserId("uid")
                .setOrderId("ORDER-28168441");
        SiftRequest request = client.buildEventRequest(fields);

        MissingFieldException missingFieldException = null;
        try {
            request.send();
        } catch (MissingFieldException e) {
            missingFieldException = e;
        }

        // Should have thrown an exception.
        Assert.assertNotNull(missingFieldException);
        Assert.assertEquals("Required field \"$api_key\" is missing.",
                missingFieldException.getLocalizedMessage());
    }

    /**
     * Custom field keys can't be empty or start with a dollar sign. This should also never fire
     * a network request so we can skip the mock web server.
     */
    @Test
    public void testInvalidCustomFieldKeyException() throws IOException {

        // Create a new client and link it to a fake server address.
        SiftClient client = new SiftClient("my_api_key");
        client.setBaseUrl(HttpUrl.parse("http://fakehost:1234"));

        // Build a simple request. The exception should happen here instead of the request send
        // method.
        InvalidFieldException invalidFieldException = null;
        try {
            new CreateOrderFieldSet()
                    .setOrderId("ORDER-28168441")
                    .setCustomField("$not_allowed", "foo");
        } catch (InvalidFieldException e) {
            invalidFieldException = e;
        }

        // Should have thrown an exception.
        Assert.assertNotNull(invalidFieldException);
        Assert.assertEquals("Custom field \"$not_allowed\" may not begin with a dollar sign.",
                invalidFieldException.getLocalizedMessage());
    }

    /**
     * The server can respond with a rate limit error and the client should turn it into an
     * exception.
     */
    @Test
    public void testRateLimitException() throws IOException {

        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"some_api_key\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$order_id\"          : \"ORDER-28168441\",\n" +
                "  \"$user_email\"          : \"bill@gmail.com\",\n" +
                "}";

        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 60,\n" +
                "    \"error_message\" : \"Rate limit error message.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("some_api_key");
        client.setBaseUrl(baseUrl);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("some_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildEventRequest(fields);
        RateLimitException rateLimitException = null;
        try {
            request.send();
        } catch (RateLimitException e) {
            rateLimitException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(rateLimitException);
        Assert.assertEquals("Rate limit error message.", rateLimitException.getLocalizedMessage());

        server.shutdown();
    }

    /**
     * If the server responds with a 500 and no response, throw a general server exception.
     */
    @Test
    public void testGenericServerErrorException() throws IOException {

        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"some_api_key\",\n" +
                "  \"$user_id\"          : \"billy_jones_301\",\n" +
                "  \"$order_id\"          : \"ORDER-28168441\",\n" +
                "  \"$user_email\"          : \"bill@gmail.com\",\n" +
                "}";

        // Start a new mock server and enqueue a mock response. Note that it has no response body.
        // We want to test that we get a reasonable ServerException in the case we get a 500 HTTP
        // code and no response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_INTERNAL_ERROR);
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("some_api_key");
        client.setBaseUrl(baseUrl);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("some_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildEventRequest(fields);
        ServerException serverException = null;
        try {
            request.send();
        } catch (ServerException e) {
            serverException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(serverException);
        Assert.assertEquals("Unexpected API error.", serverException.getLocalizedMessage());

        server.shutdown();
    }

    /**
     * Set a reserved or custom field to null? It should not be visible in the final JSON.
     */
    @Test
    public void testNullsAreNotSerialized() throws JSONException {
        CreateOrderFieldSet fieldSet = new CreateOrderFieldSet()
                .setAmount(5L)
                .setBillingAddress(null)
                .setOrderId(null)
                .setSessionId("foo")
                .setCustomField("custom1", "blah")
                .setCustomField("custom2", (Boolean) null)
                .setCustomField("custom3", 99L);

        JSONAssert.assertEquals("{" +
                "\"$type\": \"$create_order\"," +
                "\"$amount\": 5," +
                "\"$session_id\": \"foo\"," +
                "\"custom1\": \"blah\"," +
                "\"custom3\": 99," +
        "}", fieldSet.toJson(), true);
    }

}
