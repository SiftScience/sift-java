package com.siftscience;

import com.siftscience.model.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * Use a SiftClient to access all supported Sift Science APIs.
 *
 * Usage:
 *
 * SiftClient client = new SiftClient("your_api_key");
 * EventRequest txEventRequest = client.buildRequest(
 *      new TransactionFieldSet()
 *              .setUserId("some_user_id")
 *              .setAmount(506790000L)
 *              .setCurrencyCode("USD")
 *              .setTransactionType("$sale")
 *              ... );
 * try {
 *      EventResponse txEventResponse = txEventRequest.send();
 * } catch (SiftException e) {
 *     ... handle validation and unexpected server errors.
 * }
 *
 * txEventResponse.isSuccessful(); // true;
 * txEventResponse.getErrorMessage(); // "OK";
 *
 * FieldSet requestFields = txEventResponse.getRequestBody();
 * EventResponseBody responseFields = txEventResponse.getResponseBody();
 *
 */
public class SiftClient {
    private String apiKey;
    private OkHttpClient okClient = new OkHttpClient();
    private HttpUrl baseUrl = HttpUrl.parse("https://api.siftscience.com");

    public SiftClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public SiftClient setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public EventRequest buildRequest(FieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new EventRequest(baseUrl, okClient, fields);
    }

    public EventRequest buildRequest(FieldSet fields, List<String> abuseTypes) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new EventRequest(baseUrl, okClient, fields, abuseTypes);
    }

    public LabelRequest buildRequest(LabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new LabelRequest(baseUrl, okClient, fields);
    }

    public UnlabelRequest buildRequest(UnlabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new UnlabelRequest(baseUrl, okClient, fields);
    }

    public ScoreRequest buildRequest(ScoreFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new ScoreRequest(baseUrl, okClient, fields);
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
