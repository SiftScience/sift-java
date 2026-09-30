package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BotIdentification {
    @Expose @SerializedName("$result") private String result;
    @Expose @SerializedName("$provider") private String provider;

    public String getResult() {
        return result;
    }

    public BotIdentification setResult(String result) {
        this.result = result;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public BotIdentification setProvider(String provider) {
        this.provider = provider;
        return this;
    }
}
