package com.siftscience;

import com.siftscience.model.*;
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

    public SiftEventRequest buildCreateOrderRequest(String userId, CreateOrderFieldSet fields) {
        if (fields == null) {
            fields = new CreateOrderFieldSet();
        }
        return new SiftEventRequest(baseUrl, okClient,
                fields.clone().setApiKey(apiKey).setUserId(userId));
    }

    public SiftEventRequest buildUpdateOrderRequest(String userId, UpdateOrderFieldSet fields) {
        if (fields == null) {
            fields = new UpdateOrderFieldSet();
        }
        return new SiftEventRequest(baseUrl, okClient,
                fields.clone().setApiKey(apiKey).setUserId(userId));
    }

    public SiftEventRequest buildTransactionRequest(String userId, Long amount,
                                                    String currencyCode,
                                                    TransactionFieldSet fields) {
        if (fields == null) {
            fields = new TransactionFieldSet();
        }
        return new SiftEventRequest(baseUrl, okClient, fields.clone()
                .setApiKey(apiKey).setUserId(userId)
                .setAmount(amount).setCurrencyCode(currencyCode));
    }

    public SiftEventRequest buildCreateAccountRequest(String userId,
                                                      CreateAccountFieldSet fields) {
        if (fields == null) {
            fields = new CreateAccountFieldSet();
        }
        return new SiftEventRequest(baseUrl, okClient,
                fields.clone().setApiKey(apiKey).setUserId(userId));
    }

    public SiftEventRequest buildUpdateAccountRequest(String userId,
                                                      UpdateAccountFieldSet fields) {
        if (fields == null) {
            fields = new UpdateAccountFieldSet();
        }
        return new SiftEventRequest(baseUrl, okClient,
                fields.clone().setApiKey(apiKey).setUserId(userId));
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
