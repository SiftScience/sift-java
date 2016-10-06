package com.siftscience;

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

    public SiftEventRequest buildEventRequest(@NotNull FieldSet fields) {
        FieldSet clonedFields = fields.clone();
        if (clonedFields.getApiKey() == null) {
            clonedFields.setApiKey(apiKey);
        }
        return new SiftEventRequest(baseUrl, okClient, clonedFields);
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
