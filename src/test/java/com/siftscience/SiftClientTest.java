package com.siftscience;

import com.siftscience.exception.*;
import com.siftscience.model.CreateOrderFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    private final MockWebServer server = new MockWebServer();
    private SiftClient client;

    @Before
    public void setup() throws IOException {
        server.start();

        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        client = new SiftClient("your_api_key_here", "your_account_id_here");
        client.setBaseUrl(baseUrl);
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

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

        // Enqueue a mock response.
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 51,\n" +
                "    \"error_message\" : \"Invalid API key message.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("invalid_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(fields);
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

        // Enqueue a mock response.
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 55,\n" +
                "    \"error_message\" : \"Missing user email message from server.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);

        // Build a simplified request body with the "missing field".
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(fields);
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
    }

    /**
     * Custom field keys can't be empty or start with a dollar sign. This should also never fire
     * a network request so we can skip the mock web server.
     */
    @Test
    public void testInvalidCustomFieldKeyException() throws IOException {

        // Create a new client and link it to a fake server address.
        client.setBaseUrl(HttpUrl.parse("http://fakehost.invalid:1234"));

        // Build a simple request. The exception should happen here instead of the request send
        // method.
        InvalidFieldException invalidFieldException = null;
        try {
            client.buildRequest(new CreateOrderFieldSet()
                    .setOrderId("ORDER-28168441")
                    .setCustomField("$not_allowed", "foo")).send();
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

        // Enqueue a mock response.
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_BAD_REQUEST);
        response.setBody("{\n" +
                "    \"status\" : 60,\n" +
                "    \"error_message\" : \"Rate limit error message.\",\n" +
                "    \"time\" : 1327604222,\n" +
                "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
                "}");
        server.enqueue(response);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("some_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(fields);
        RateLimitException rateLimitException = null;
        try {
            request.send();
        } catch (RateLimitException e) {
            rateLimitException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(rateLimitException);
        Assert.assertEquals("Rate limit error message.", rateLimitException.getLocalizedMessage());
    }

    /**
     * If the server responds with a 500 and no response, throw a general server exception.
     */
    @Test
    public void testGenericServerErrorException() throws IOException {

        // Enqueue a mock response. Note that it has no response body. We want to test that we
        // get a reasonable ServerException in the case we get a 500 HTTP code and no response.
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_INTERNAL_ERROR);
        server.enqueue(response);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("some_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(fields);
        ServerException serverException = null;
        try {
            request.send();
        } catch (ServerException e) {
            serverException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(serverException);
        Assert.assertEquals("Unexpected API error.", serverException.getLocalizedMessage());
    }

    /**
     * If the server responds with a 500 and a non-JSON response, throw a general server exception.
     */
    @Test
    public void testGenericServerErrorExceptionNonJson() throws IOException {

        // Enqueue a mock response. Note that it has no response body. We want to test that we
        // get a reasonable ServerException in the case we get a 500 HTTP code and no response.
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_INTERNAL_ERROR);
        response.setBody("<html><body>Oops</body></html>");
        server.enqueue(response);

        // Build a simplified request body.
        CreateOrderFieldSet fields = new CreateOrderFieldSet()
                .setApiKey("some_api_key")
                .setUserId("billy_jones_301")
                .setOrderId("ORDER-28168441")
                .setUserEmail("bill@gmail.com");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(fields);
        ServerException serverException = null;
        try {
            request.send();
        } catch (ServerException e) {
            serverException = e;
        }

        // We should have gotten an exception.
        Assert.assertNotNull(serverException);
        Assert.assertEquals("Unexpected API error.", serverException.getLocalizedMessage());
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
