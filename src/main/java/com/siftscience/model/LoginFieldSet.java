package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginFieldSet extends EventsApiRequestFieldSet<LoginFieldSet> {
    public static LoginFieldSet fromJson(String json) {
        return gson.fromJson(json, LoginFieldSet.class);
    }

    @Expose @SerializedName("$login_status") private String loginStatus;
    @Expose @SerializedName("$browser") private Browser browser;
    @Expose @SerializedName("$app") private App app;

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

    public Browser getBrowser() {
        return browser;
    }

    public LoginFieldSet setBrowser(Browser browser) {
        this.browser = browser;
        return this;
    }

    public App getApp() {
        return app;
    }

    public LoginFieldSet setApp(App app) {
        this.app = app;
        return this;
    }
}
