package com.siftscience;

import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.UpdateOrderFieldSet;
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

    public SiftEventRequest buildCreateOrderRequest(
            String userId,
            CreateOrderFieldSet extraFields) {

        extraFields.setApiKey(apiKey).setUserId(userId);
        return new SiftEventRequest(baseUrl, okClient, extraFields);
    }

    public SiftEventRequest buildUpdateOrderRequest(
            String userId,
            UpdateOrderFieldSet extraFields) {

        extraFields.setApiKey(apiKey).setUserId(userId);
        return new SiftEventRequest(baseUrl, okClient, extraFields);
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
