package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlagContentFieldSet extends EventsApiRequestFieldSet<FlagContentFieldSet> {
    public static FlagContentFieldSet fromJson(String json) {
        return gson.fromJson(json, FlagContentFieldSet.class);
    }

    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$flagged_by") private String flaggedBy;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    @Override
    public String getEventType() {
        return "$flag_content";
    }

    public String getContentId() {
        return contentId;
    }

    public FlagContentFieldSet setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getFlaggedBy() {
        return flaggedBy;
    }

    public FlagContentFieldSet setFlaggedBy(String flaggedBy) {
        this.flaggedBy = flaggedBy;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public FlagContentFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }
}
