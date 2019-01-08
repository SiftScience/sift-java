package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginFieldSet extends BaseAppBrowserFieldSet<LoginFieldSet> {
    public static LoginFieldSet fromJson(String json) {
        return gson.fromJson(json, LoginFieldSet.class);
    }

    @Expose @SerializedName("$login_status") private String loginStatus;
    @Expose @SerializedName("$failure_reason") private String failureReason;
    @Expose @SerializedName("$username") private String username;
    @Expose @SerializedName("$social_sign_on_type") private String socialSignOnType;
    @Expose @SerializedName("$account_types") private List<String> accountTypes;

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

    public String getFailureReason() { return failureReason; }

    public LoginFieldSet setFailureReason(String failureReason) {
        this.failureReason = failureReason;
        return this;
    }

    public String getUsername() { return username; }

    public LoginFieldSet setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSocialSignOnType() { return socialSignOnType; }

    public LoginFieldSet setSocialSignOnType(String socialSignOnType) {
        this.socialSignOnType = socialSignOnType;
        return this;
    }

    public List<String> getAccountTypes() {
        return accountTypes;
    }

    public LoginFieldSet setAccountTypes(List<String> accountTypes) {
        this.accountTypes = accountTypes;
        return this;
    }
}
