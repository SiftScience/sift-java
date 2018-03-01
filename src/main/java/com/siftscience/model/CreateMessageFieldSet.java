package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMessageFieldSet extends CreateContentFieldSet<CreateMessageFieldSet> {
    public static CreateMessageFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateMessageFieldSet.class);
    }

    @Expose @SerializedName("$message") private Message message;

    public Message getMessage() {
        return message;
    }

    public CreateMessageFieldSet setMessage(Message message) {
        this.message = message;
        return this;
    }
}
