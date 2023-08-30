package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationResponseBody extends BaseResponseBody<VerificationResponseBody> {

    @Expose @SerializedName("sent_at") private Long sentAt;

    @Expose @SerializedName("checked_at") private Long checkedAt;

    public Long getCheckedAt() {
        return checkedAt;
    }

    public VerificationResponseBody setCheckedAt(Long checkedAt) {
        this.checkedAt = checkedAt;
        return this;
    }

    public static VerificationResponseBody fromJson(String json) {
        return gson.fromJson(json, VerificationResponseBody.class);
    }

    public Long getSentAt() {
        return sentAt;
    }

    public VerificationResponseBody setSentAt(Long sentAt) {
        this.sentAt = sentAt;
        return this;
    }
}

