package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMessageFieldSet extends UpdateContentFieldSet<UpdateMessageFieldSet> {
    public static UpdateMessageFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateMessageFieldSet.class);
    }

    @Expose @SerializedName("$message") private Message message;

    public Message getMessage() {
        return message;
    }

    public UpdateMessageFieldSet setMessage(Message message) {
        this.message = message;
        return this;
    }
}
