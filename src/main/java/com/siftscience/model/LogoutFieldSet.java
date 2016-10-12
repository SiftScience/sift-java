package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class LogoutFieldSet extends EventsApiRequestFieldSet<LogoutFieldSet> {

    public static LogoutFieldSet fromJson(String json) {
        return gson.fromJson(json, LogoutFieldSet.class);
    }

    @Override
    public String getEventType() {
        return "$logout";
    }
}
