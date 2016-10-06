package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkSessionToUserFieldSet extends FieldSet<LinkSessionToUserFieldSet> {

    public static LinkSessionToUserFieldSet fromJson(String json) {
        return gson.fromJson(json, LinkSessionToUserFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$link_session_to_user";
    }

    @Override
    public void validate() {
        validateApiKey();
        validateUserId();
        validateSessionId();
    }

    public String getUserId() {
        return userId;
    }

    public LinkSessionToUserFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LinkSessionToUserFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

}
