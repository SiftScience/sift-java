package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class CustomEventFieldSet extends EventsApiRequestFieldSet<CustomEventFieldSet> {

    public static CustomEventFieldSet fromJson(String json) {
        return gson.fromJson(json, CustomEventFieldSet.class);
    }

    @Expose @SerializedName(EVENT_TYPE) private String eventType;

    @Override
    public String getEventType() {
        return eventType;
    }

    public CustomEventFieldSet setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }
}
