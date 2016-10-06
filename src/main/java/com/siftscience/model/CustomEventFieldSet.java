package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomEventFieldSet extends FieldSet<CustomEventFieldSet> {

    public static CustomEventFieldSet fromJson(String json) {
        return gson.fromJson(json, CustomEventFieldSet.class);
    }

    @Expose @SerializedName(EVENT_TYPE) private String eventType;
    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    public CustomEventFieldSet setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public CustomEventFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public CustomEventFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public CustomEventFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CustomEventFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
