package com.siftscience;

import com.siftscience.model.GlobalProfileFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;

public class GlobalProfileTest {

    private static final String RESPONSE_BODY = "{\n" +
            "  \"status\": 0,\n" +
            "  \"error_message\": \"OK\",\n" +
            "  \"error_code\": null,\n" +
            "  \"lookback_months\": 12,\n" +
            "  \"profile_summary\": {\n" +
            "    \"identity_found\": true,\n" +
            "    \"has_links\": true,\n" +
            "    \"link_count\": 7,\n" +
            "    \"linked_accounts_count_per_industry\": {\"finances\": 3, \"internet\": 4}\n" +
            "  },\n" +
            "  \"identity_age\": {\n" +
            "    \"oldest_account_age_timestamp\": 1681090536,\n" +
            "    \"newest_account_age_timestamp\": 1881090536,\n" +
            "    \"average_account_age_timestamp\": 1781090536\n" +
            "  },\n" +
            "  \"user_decisions\": {\n" +
            "    \"total\": 12, \"blocked\": 2, \"watched\": 3, \"accepted\": 6,\n" +
            "    \"manual\": 4, \"auto\": 8, \"last_type\": \"BLOCK\", \"last_timestamp\": 1881090536\n" +
            "  },\n" +
            "  \"chargebacks\": {\n" +
            "    \"total\": 3, \"fraudulent\": 2, \"other\": 1,\n" +
            "    \"last_timestamp\": 1881090536, \"last_fraudulent_timestamp\": 1881090536\n" +
            "  },\n" +
            "  \"orders\": {\n" +
            "    \"total\": 50, \"blocked\": 2, \"watched\": 5, \"accepted\": 40,\n" +
            "    \"last_timestamp\": 1881090536, \"last_blocked_timestamp\": 1881090536\n" +
            "  },\n" +
            "  \"transactions\": {\n" +
            "    \"total\": 120, \"failed_fraud\": 3, \"failed_other\": 5, \"successful\": 112,\n" +
            "    \"last_timestamp\": 1881090536, \"last_failed_fraud_timestamp\": 1881090536\n" +
            "  },\n" +
            "  \"locations\": {\n" +
            "    \"unique_billing_addresses\": 2, \"unique_shipping_addresses\": 4,\n" +
            "    \"distinct_countries_count\": 3, \"distinct_regions_count\": 5,\n" +
            "    \"location_connected_accounts\": [{\"city\": \"Kyiv\", \"country\": \"UA\"}],\n" +
            "    \"location_last_used_timestamp\": 1881090536\n" +
            "  }\n" +
            "}";

    @Test
    public void testGetGlobalProfile() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(RESPONSE_BODY);
        server.enqueue(response);
        server.start();

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        // Build and execute the request against the mock server.
        GlobalProfileRequest getGlobalProfileRequest = client.buildRequest(
                new GlobalProfileFieldSet()
                        .setUserId("some_user_id")
                        .setGlobalOnly(true)
                        .setIncludeOwnData(false));

        GlobalProfileResponse siftResponse = getGlobalProfileRequest.send();

        // Verify the request.
        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/v3/accounts/" + accountId +
                        "/global_profile/users/some_user_id?global_only=true&include_own_data=false",
                request.getPath());
        Assert.assertEquals(request.getHeader("Authorization"), "Basic WU9VUl9BUElfS0VZOg==");

        // Verify the response was parsed correctly.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
                siftResponse.getBody().toJson(), true);

        Assert.assertTrue(siftResponse.getBody().getProfileSummary().getIdentityFound());
        Assert.assertEquals(Long.valueOf(7), siftResponse.getBody().getProfileSummary().getLinkCount());
        Assert.assertEquals(Integer.valueOf(12), siftResponse.getBody().getLookbackMonths());
        Assert.assertEquals("UA",
                siftResponse.getBody().getLocations().getLocationConnectedAccounts().get(0).getCountry());
    }

    @Test
    public void testGetGlobalProfileWithoutOptionalParams() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(RESPONSE_BODY);
        server.enqueue(response);
        server.start();

        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        GlobalProfileRequest getGlobalProfileRequest = client.buildRequest(
                new GlobalProfileFieldSet().setUserId("some_user_id"));
        getGlobalProfileRequest.send();

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/v3/accounts/" + accountId + "/global_profile/users/some_user_id",
                request.getPath());
    }

    @Test
    public void testIdentityNotFound() throws Exception {
        String accountId = "YOUR_ACCOUNT_ID";
        String responseBody = "{\n" +
                "  \"status\": 0,\n" +
                "  \"error_message\": \"OK\",\n" +
                "  \"error_code\": null,\n" +
                "  \"lookback_months\": null,\n" +
                "  \"profile_summary\": {\n" +
                "    \"identity_found\": false,\n" +
                "    \"has_links\": null,\n" +
                "    \"link_count\": null,\n" +
                "    \"linked_accounts_count_per_industry\": null\n" +
                "  },\n" +
                "  \"identity_age\": null,\n" +
                "  \"user_decisions\": null,\n" +
                "  \"chargebacks\": null,\n" +
                "  \"orders\": null,\n" +
                "  \"transactions\": null,\n" +
                "  \"locations\": null\n" +
                "}";

        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody(responseBody);
        server.enqueue(response);
        server.start();

        SiftClient client = new SiftClient("YOUR_API_KEY", accountId,
                new OkHttpClient.Builder()
                        .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                        .build());

        GlobalProfileRequest getGlobalProfileRequest = client.buildRequest(
                new GlobalProfileFieldSet().setUserId("unknown_user_id"));
        GlobalProfileResponse siftResponse = getGlobalProfileRequest.send();

        Assert.assertFalse(siftResponse.getBody().getProfileSummary().getIdentityFound());
        Assert.assertNull(siftResponse.getBody().getIdentityAge());
        Assert.assertNull(siftResponse.getBody().getUserDecisions());
    }
}
