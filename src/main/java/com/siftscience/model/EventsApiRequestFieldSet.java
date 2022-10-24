package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public abstract class EventsApiRequestFieldSet<T extends EventsApiRequestFieldSet<T>> extends
        FieldSet<T> {
    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Long time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName(KEYLESS_USER_ID) private String keylessUserId;

    public T setCustomField(String key, Number val) {
        return super.setCustomField(key, val);
    }

    public T setCustomField(String key, Boolean val) {
        return super.setCustomField(key, val);
    }

    public T setCustomField(String key, String val) {
        return super.setCustomField(key, val);
    }

    protected T clearCustomField(String key) {
        return super.clearCustomField(key);
    }

    protected T clearCustomFields() {
        return super.clearCustomFields();
    }

    @Override
    protected boolean shouldJsonSerializeApiKey() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public T setUserId(String userId) {
        this.userId = userId;
        return (T) this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public T setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return (T) this;
    }

    public Long getTime() {
        return time;
    }

    public T setTime(Long time) {
        this.time = time;
        return (T) this;
    }

    public String getIp() {
        return ip;
    }

    public T setIp(String ip) {
        this.ip = ip;
        return (T) this;
    }

    public String getKeylessUserId() {
        return keylessUserId;
    }

    public T setKeylessUserId(String keylessUserId) {
        this.keylessUserId = keylessUserId;
        return (T) this;
    }
}
