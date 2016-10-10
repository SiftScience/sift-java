package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class SendMessageFieldSet extends FieldSet<SendMessageFieldSet> {

    public static SendMessageFieldSet fromJson(String json) {
        return gson.fromJson(json, SendMessageFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$recipient_user_id") private String recipientUserId;
    @Expose @SerializedName("$subject") private String subject;
    @Expose @SerializedName("$content") private String content;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$send_message";
    }

    public String getUserId() {
        return userId;
    }

    public SendMessageFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public SendMessageFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getRecipientUserId() {
        return recipientUserId;
    }

    public SendMessageFieldSet setRecipientUserId(String recipientUserId) {
        this.recipientUserId = recipientUserId;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public SendMessageFieldSet setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SendMessageFieldSet setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public SendMessageFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public SendMessageFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
