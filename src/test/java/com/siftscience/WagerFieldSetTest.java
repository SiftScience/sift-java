package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.ExchangeRate;
import com.siftscience.model.WagerFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class WagerFieldSetTest {
    @Test
    public void testWagerEvent() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"                 : \"$wager\",\n" +
            "  \"$api_key\"              : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"              : \"billy_jones_301\",\n" +
            "  \"$wager_id\"             : \"wager_id_123\",\n" +
            "  \"$wager_type\"           : \"win_1\",\n" +
            "  \"$wager_status\"         : \"$accept\",\n" +
            "  \"$amount\"               : 506790000,\n" +
            "  \"$currency_code\"        : \"EUR\",\n" +
            "  \"$exchange_rate\"        : {\n" +
            "      \"$quote_currency_code\" : \"USD\",\n" +
            "      \"$rate\"                : 1.15\n" +
            "  },\n" +
            "  \"$wager_event_type\"     : \"sportsbook\",\n" +
            "  \"$wager_event_name\"     : \"NFL\",\n" +
            "  \"$wager_event_id\"       : \"NFL_2024_N1234\",\n" +
            "  \"$minimum_wager_amount\" : 1000000,\n" +
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
        EventRequest request = client.buildRequest(new WagerFieldSet()
            .setUserId("billy_jones_301")
            .setWagerId("wager_id_123")
            .setWagerType("win_1")
            .setWagerStatus("$accept")
            .setAmount(506790000L)
            .setCurrencyCode("EUR")
            .setExchangeRate(new ExchangeRate()
                .setQuoteCurrencyCode("USD")
                .setRate(1.15f))
            .setWagerEventType("sportsbook")
            .setWagerEventName("NFL")
            .setWagerEventId("NFL_2024_N1234")
            .setMinimumWagerAmount(1000000L));
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
