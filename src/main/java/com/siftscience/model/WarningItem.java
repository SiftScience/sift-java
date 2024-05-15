package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarningItem extends BaseResponseBody<WarningItem> {
    @Expose
    @SerializedName("message")
    private String message;

    public static WarningItem fromJson(String jsonBody) {
        return gson.fromJson(jsonBody, WarningItem.class);
    }

    public String getMessage() {
        return message;
    }

    public WarningItem setMessage(String message) {
        this.message = message;
        return this;
    }
}
