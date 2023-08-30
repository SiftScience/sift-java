package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class VerificationCheckFieldSet extends FieldSet<VerificationCheckFieldSet> {

    public static VerificationCheckFieldSet fromJson(String json) {
        return gson.fromJson(json, VerificationCheckFieldSet.class);
    }
    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName("$code") private String code;
    @Expose @SerializedName("$verified_event") private String verifiedEvent;
    @Expose @SerializedName("$verified_entity_id") private String verifiedEntityId;

    public String getUserId() {
        return userId;
    }

    public VerificationCheckFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getVerifiedEvent() {
        return verifiedEvent;
    }

    public VerificationCheckFieldSet setVerifiedEvent(String verifiedEvent) {
        this.verifiedEvent = verifiedEvent;
        return this;
    }

    public String getVerifiedEntityId() {
        return verifiedEntityId;
    }

    public VerificationCheckFieldSet setVerifiedEntityId(String verifiedEntityId) {
        this.verifiedEntityId = verifiedEntityId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public VerificationCheckFieldSet setCode(String code) {
        this.code = code;
        return this;
    }
}
