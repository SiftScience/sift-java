package com.siftscience;

import com.siftscience.exception.MissingFieldException;
import com.siftscience.exception.SiftException;
import com.siftscience.model.*;
import com.sun.istack.internal.NotNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

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

    public SiftEventRequest buildRequest(@NotNull FieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new SiftEventRequest(baseUrl, okClient, fields);
    }

    public SiftLabelRequest buildRequest(@NotNull LabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new SiftLabelRequest(baseUrl, okClient, fields);
    }

    public SiftUnlabelRequest buildRequest(@NotNull UnlabelFieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(apiKey);
        }
        return new SiftUnlabelRequest(baseUrl, okClient, fields);
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
