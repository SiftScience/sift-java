package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Browser {
    @Expose @SerializedName("$user_agent") private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public Browser setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
}
