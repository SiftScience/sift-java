package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlagContentFieldSet extends FieldSet<FlagContentFieldSet> {

    public static FlagContentFieldSet fromJson(String json) {
        return gson.fromJson(json, FlagContentFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$flagged_by") private String flaggedBy;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$flag_content";
    }

    @Override
    public void validate() {
        super.validate();
        validateStringField("$content_id", getContentId());
    }

    public String getUserId() {
        return userId;
    }

    public FlagContentFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public FlagContentFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
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
}
