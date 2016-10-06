package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutFieldSet extends FieldSet<LogoutFieldSet> {

    public static LogoutFieldSet fromJson(String json) {
        return gson.fromJson(json, LogoutFieldSet.class);
    }

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
        return "$logout";
    }

    public String getUserId() {
        return userId;
    }

    public LogoutFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LogoutFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public LogoutFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogoutFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
