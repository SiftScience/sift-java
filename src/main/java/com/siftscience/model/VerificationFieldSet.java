package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationFieldSet extends EventsApiRequestFieldSet<VerificationFieldSet> {
    public static VerificationFieldSet fromJson(String json) {
        return gson.fromJson(json, VerificationFieldSet.class);
    }

    @Expose @SerializedName("$status") private String status;
    @Expose @SerializedName("$verification_type") private String verificationType;
    @Expose @SerializedName("$verified_value") private String verifiedValue;

    @Override
    public String getEventType() {
        return "$verification";
    }

    public String getStatus() {
        return status;
    }

    public VerificationFieldSet setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getVerificationType() {
        return verificationType;
    }

    public VerificationFieldSet setVerificationType(String verificationType) {
        this.verificationType = verificationType;
        return this;
    }

    public String getVerifiedValue() {
        return verifiedValue;
    }

    public VerificationFieldSet setVerifiedValue(String verifiedValue) {
        this.verifiedValue = verifiedValue;
        return this;
    }
}
