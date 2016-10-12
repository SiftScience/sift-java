package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class SendMessageFieldSet extends EventsApiRequestFieldSet<SendMessageFieldSet> {

    public static SendMessageFieldSet fromJson(String json) {
        return gson.fromJson(json, SendMessageFieldSet.class);
    }

    @Expose @SerializedName("$recipient_user_id") private String recipientUserId;
    @Expose @SerializedName("$subject") private String subject;
    @Expose @SerializedName("$content") private String content;

    @Override
    public String getEventType() {
        return "$send_message";
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
}
