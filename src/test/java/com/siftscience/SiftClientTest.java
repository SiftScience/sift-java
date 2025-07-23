package com.siftscience;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import com.siftscience.exception.InvalidApiKeyException;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.exception.MissingFieldException;
import com.siftscience.exception.RateLimitException;
import com.siftscience.exception.ServerException;
import com.siftscience.model.ApplyDecisionFieldSet;
import com.siftscience.model.CreateMerchantFieldSet;
import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.DecisionStatusFieldSet;
import com.siftscience.model.GetDecisionFieldSet;
import com.siftscience.model.GetMerchantsFieldSet;
import com.siftscience.model.UpdateMerchantFieldSet;
import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

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

        // Create a new client and link it to the mock server.
        client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());
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
        // Create a new client with an invalid API key
        client = new SiftClient("INVALID_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        String expectedRequestBody = "{\n" +
                "  \"$type\"             : \"$create_order\",\n" +
                "  \"$api_key\"          : \"INVALID_API_KEY\",\n" +
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
        assertNotNull(apiKeyException);
        assertEquals("Invalid API key message.", apiKeyException.getLocalizedMessage());

        // Check that we can access the API key from the exception object.
        assertEquals("INVALID_API_KEY",
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
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
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
        assertNotNull(missingFieldException);
        assertEquals("Missing user email message from server.",
                missingFieldException.getLocalizedMessage());
    }

    /**
     * Custom field keys can't be empty or start with a dollar sign. This should also never fire
     * a network request so we can skip the mock web server.
     */
    @Test
    public void testInvalidCustomFieldKeyException() throws IOException {
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
        assertNotNull(invalidFieldException);
        assertEquals("Custom field \"$not_allowed\" may not begin with a dollar sign.",
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
                "  \"$api_key\"          : \"YOUR_API_KEY\",\n" +
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
        assertNotNull(rateLimitException);
        assertEquals("Rate limit error message.", rateLimitException.getLocalizedMessage());
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
        assertNotNull(serverException);
        assertEquals("Unexpected API error " + HTTP_INTERNAL_ERROR + ".",
            serverException.getLocalizedMessage());
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
        assertNotNull(serverException);
        assertEquals("Unexpected API error " + HTTP_INTERNAL_ERROR + ".",
            serverException.getLocalizedMessage());
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
                "\"custom3\": 99" +
        "}", fieldSet.toJson(), true);
    }

    @Test
    public void testFailsToCreateClientWhenApiKeyIsNull() {
        assertIllegalArgumentWithMessage(
            () -> new SiftClient(null, "YOUR_ACCOUNT_ID"),
            "API key must not be null"
        );
    }

    @Test
    public void testFailsToCreateClientWhenHttpClientIsNull() {
        assertIllegalArgumentWithMessage(
            () -> new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
                (OkHttpClient) null),
            "Http Client must not be null"
        );
    }

    @Test
    public void testFailsToBuildApplyDecisionRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new ApplyDecisionFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildGetDecisionRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new GetDecisionFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildDecisionStatusRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new DecisionStatusFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildWorkflowStatusRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new WorkflowStatusFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildGetMerchantsRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new GetMerchantsFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildCreateMerchantRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new CreateMerchantFieldSet()),
            "Account ID must not be null"
        );
    }

    @Test
    public void testFailsToBuildUpdateMerchantRequest() {
        SiftClient siftClient = new SiftClient("YOUR_API_KEY", null);
        assertIllegalArgumentWithMessage(
            () -> siftClient.buildRequest(new UpdateMerchantFieldSet(), "merchantID"),
            "Account ID must not be null"
        );
    }

    private void assertIllegalArgumentWithMessage(Runnable runnable, String message) {
        try {
            runnable.run();
            fail("Excepted to throw IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals(message, exception.getMessage());
        }
    }
}
