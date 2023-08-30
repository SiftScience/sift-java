package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationResponseBody extends BaseResponseBody<VerificationResponseBody> {
    @Expose @SerializedName("sent_at") private Double sentAt;

    public static VerificationResponseBody fromJson(String json) {
        return gson.fromJson(json, VerificationResponseBody.class);
    }

    public Double getSentAt() {
        return sentAt;
    }

    public VerificationResponseBody setSentAt(Double sentAt) {
        this.sentAt = sentAt;
        return this;
    }
}

