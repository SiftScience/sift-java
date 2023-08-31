package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationResendResponseBody extends BaseResponseBody<VerificationResendResponseBody> {

    @Expose @SerializedName("sent_at") private Long sentAt;


    public static VerificationResendResponseBody fromJson(String json) {
        return gson.fromJson(json, VerificationResendResponseBody.class);
    }

    public Long getSentAt() {
        return sentAt;
    }

    public VerificationResendResponseBody setSentAt(Long sentAt) {
        this.sentAt = sentAt;
        return this;
    }

}

