package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationCheckResponseBody extends BaseResponseBody<VerificationCheckResponseBody> {

    @Expose @SerializedName("checked_at") private Long checkedAt;

    public static VerificationCheckResponseBody fromJson(String json) {
        return gson.fromJson(json, VerificationCheckResponseBody.class);
    }

    public Long getCheckedAt() {
        return checkedAt;
    }

    public VerificationCheckResponseBody setCheckedAt(Long checkedAt) {
        this.checkedAt = checkedAt;
        return  this;
    }
}

