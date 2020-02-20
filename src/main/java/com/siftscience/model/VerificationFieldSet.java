package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationFieldSet extends BaseAppBrowserSiteBrandFieldSet<VerificationFieldSet> {
    public static VerificationFieldSet fromJson(String json) {
        return gson.fromJson(json, VerificationFieldSet.class);
    }

    @Expose @SerializedName("$status") private String status;
    @Expose @SerializedName("$verification_type") private String verificationType;
    @Expose @SerializedName("$verified_value") private String verifiedValue;
    @Expose @SerializedName("$reason") private String reason;
    @Expose @SerializedName("$verified_event") private String verifiedEvent;
    @Expose @SerializedName("$verified_entity_id") private String verifiedEntityId;

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

    public String getReason() { return reason; }

    public VerificationFieldSet setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getVerifiedEvent() { return verifiedEvent; }

    public VerificationFieldSet setVerifiedEvent(String verifiedEvent) {
        this.verifiedEvent = verifiedEvent;
        return this;
    }

    public String getVerifiedEntityId() { return verifiedEntityId; }

    public VerificationFieldSet setVerifiedEntityId(String verifiedEntityId) {
        this.verifiedEntityId = verifiedEntityId;
        return this;
    }
}
