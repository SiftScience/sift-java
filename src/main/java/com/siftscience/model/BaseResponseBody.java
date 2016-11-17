package com.siftscience.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseResponseBody<T extends BaseResponseBody<T>> {
    // This static gson instance is used to deserialize JSON responses from all Sift APIs.
    protected static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Expose @SerializedName("status") private Integer status;
    @Expose @SerializedName("error_message") private String errorMessage;

    public Integer getStatus() {
        return status;
    }

    protected T setStatus(Integer status) {
        this.status = status;
        return (T) this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    protected T setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return (T) this;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
