package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginFieldSet extends BaseAppBrowserFieldSet<LoginFieldSet> {
    public static LoginFieldSet fromJson(String json) {
        return gson.fromJson(json, LoginFieldSet.class);
    }

    @Expose @SerializedName("$login_status") private String loginStatus;

    @Override
    public String getEventType() {
        return "$login";
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public LoginFieldSet setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }
}
