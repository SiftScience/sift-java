package com.siftscience.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class MerchantBaseResponseBody<T extends MerchantBaseResponseBody<T>> {
    // This static gson instance is used to deserialize JSON responses from all Sift APIs.
    protected static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Expose @SerializedName("error") private String error;

    public String getError() {
        return error;
    }

    protected T setError(String error) {
        this.error = error;
        return (T) this;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
