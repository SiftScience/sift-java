package com.siftscience;

import com.siftscience.model.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * Use a SiftClient to access all supported Sift Science APIs. It may be used concurrently from
 * different threads.
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
 * txEventResponse.isOk(); // true;
 * txEventResponse.getApiErrorMessage(); // "OK";
 *
 * FieldSet requestFields = txEventResponse.getRequestBody();
 * EventResponseBody responseFields = txEventResponse.getBody();
 *
 */
public class SiftClient {
    private String apiKey;
    private OkHttpClient okClient = new OkHttpClient();
    private HttpUrl baseUrl = HttpUrl.parse("https://api.siftscience.com");
    private HttpUrl baseApi3Url = HttpUrl.parse("https://api3.siftscience.com");

    public SiftClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public EventRequest buildRequest(FieldSet fields) {
        setupApiKey(fields);
        return new EventRequest(baseUrl, okClient, fields);
    }

    public ApplyDecisionRequest buildRequest(ApplyDecisionFieldSet fields) {
        setupApiKey(fields);
        return new ApplyDecisionRequest(baseApi3Url, okClient, fields);
    }
    
    public GetDecisionsRequest buildRequest(GetDecisionFieldSet fields) {
        setupApiKey(fields);
        return new GetDecisionsRequest(baseApi3Url, okClient, fields);
    }

    public DecisionStatusRequest buildRequest(DecisionStatusFieldSet fields) {
        setupApiKey(fields);
        return new DecisionStatusRequest(baseApi3Url, okClient, fields);
    }

    public LabelRequest buildRequest(LabelFieldSet fields) {
        setupApiKey(fields);
        return new LabelRequest(baseUrl, okClient, fields);
    }

    public UnlabelRequest buildRequest(UnlabelFieldSet fields) {
        setupApiKey(fields);
        return new UnlabelRequest(baseUrl, okClient, fields);
    }

    public ScoreRequest buildRequest(ScoreFieldSet fields) {
        setupApiKey(fields);
        return new ScoreRequest(baseUrl, okClient, fields);
    }

    public WorkflowStatusRequest buildRequest(WorkflowStatusFieldSet fields) {
        setupApiKey(fields);
        return new WorkflowStatusRequest(baseApi3Url, okClient, fields);
    }

    private void setupApiKey(FieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
    SiftClient setBaseApi3Url(HttpUrl baseApi3Url) {
        this.baseApi3Url = baseApi3Url;
        return this;
    }
}
