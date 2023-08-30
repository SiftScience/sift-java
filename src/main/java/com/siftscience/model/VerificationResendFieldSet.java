package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class VerificationResendFieldSet extends FieldSet<VerificationResendFieldSet> {
    public static VerificationResendFieldSet fromJson(String json) {
        return gson.fromJson(json, VerificationResendFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName("$verified_event") private String verifiedEvent;
    @Expose @SerializedName("$verified_entity_id") private String verifiedEntityId;

    public String getUserId() {
        return userId;
    }

    public VerificationResendFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getVerifiedEvent() {
        return verifiedEvent;
    }

    public VerificationResendFieldSet setVerifiedEvent(String verifiedEvent) {
        this.verifiedEvent = verifiedEvent;
        return this;
    }

    public String getVerifiedEntityId() {
        return verifiedEntityId;
    }

    public VerificationResendFieldSet setVerifiedEntityId(String verifiedEntityId) {
        this.verifiedEntityId = verifiedEntityId;
        return this;
    }
}
