package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentStatusFieldSet extends FieldSet<ContentStatusFieldSet> {

    public static ContentStatusFieldSet fromJson(String json) {
        return gson.fromJson(json, ContentStatusFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$status") private String status;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$content_status";
    }

    @Override
    public void validate() {
        super.validate();
        validateStringField("$content_id", getContentId());
        validateStringField("$status", getStatus());

    }

    public String getUserId() {
        return userId;
    }

    public ContentStatusFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ContentStatusFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ContentStatusFieldSet setStatus(String status) {
        this.status = status;
        return this;
    }


    public String getContentId() {
        return contentId;
    }

    public ContentStatusFieldSet setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }
}
