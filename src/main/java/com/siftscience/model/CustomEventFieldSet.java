package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomEventFieldSet extends BaseAppBrowserSiteBrandFieldSet<CustomEventFieldSet> {
    public static CustomEventFieldSet fromJson(String json) {
        return gson.fromJson(json, CustomEventFieldSet.class);
    }

    @Expose @SerializedName(EVENT_TYPE) private String eventType;
    @Expose @SerializedName("$user_email") private String userEmail;

    @Override
    public String getEventType() {
        return eventType;
    }

    public CustomEventFieldSet setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public CustomEventFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
