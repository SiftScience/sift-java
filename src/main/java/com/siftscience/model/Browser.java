package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Browser {
    private static final String APP_VERSION = "3.7.0";
    private static final String API_VERSION = "v205";
    @Expose @SerializedName("$user_agent") private String userAgent = String.format("SiftScience/%s sift-java/%s", API_VERSION, APP_VERSION);
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
