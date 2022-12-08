package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Browser {
    @Expose @SerializedName("$user_agent") private String userAgent;
    @Expose @SerializedName("$accept_language") private String acceptLanguage;
    @Expose @SerializedName("$content_language") private String contentLanguage;

    public String getUserAgent() {
        return userAgent;
    }

    public Browser setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public Browser setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public Browser setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
        return this;
    }

}
