package com.siftscience;

import com.siftscience.model.*;
import com.sun.istack.internal.NotNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

public class SiftClient {
    private String apiKey;
    private OkHttpClient okClient = new OkHttpClient();
    private HttpUrl baseUrl = HttpUrl.parse("https://api.siftscience.com");

    public SiftClient() { }

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

    public EventRequest buildRequest(@NotNull FieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new EventRequest(baseUrl, okClient, fields);
    }

    public EventRequest buildRequest(@NotNull FieldSet fields, boolean returnScore) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new EventRequest(baseUrl, okClient, fields, returnScore);
    }

    public EventRequest buildRequest(@NotNull FieldSet fields, boolean returnScore,
                                     List<String> abuseTypes) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new EventRequest(baseUrl, okClient, fields, returnScore, abuseTypes);
    }

    public LabelRequest buildRequest(@NotNull LabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new LabelRequest(baseUrl, okClient, fields);
    }

    public UnlabelRequest buildRequest(@NotNull UnlabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new UnlabelRequest(baseUrl, okClient, fields);
    }

    public ScoreRequest buildRequest(@NotNull ScoreFieldSet fields) {
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
