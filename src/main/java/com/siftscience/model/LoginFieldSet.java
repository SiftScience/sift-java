package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginFieldSet extends BaseAppBrowserSiteBrandFieldSet<LoginFieldSet> {
    public static LoginFieldSet fromJson(String json) {
        return gson.fromJson(json, LoginFieldSet.class);
    }

    @Expose @SerializedName("$login_status") private String loginStatus;
    @Expose @SerializedName("$failure_reason") private String failureReason;
    @Expose @SerializedName("$username") private String username;
    @Expose @SerializedName("$social_sign_on_type") private String socialSignOnType;
    @Expose @SerializedName("$account_types") private List<String> accountTypes;
    @Expose @SerializedName(USER_EMAIL) private String userEmail;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;
    @Expose @SerializedName("$geo") private Geo geo;
    @Expose @SerializedName("$bot_identification") private BotIdentification botIdentification;

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

    public String getUserEmail() {
        return userEmail;
    }

    public LoginFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public LoginFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }

    public Geo getGeo() {
        return geo;
    }

    public LoginFieldSet setGeo(Geo geo) {
        this.geo = geo;
        return this;
    }

    public BotIdentification getBotIdentification() {
        return botIdentification;
    }

    public LoginFieldSet setBotIdentification(BotIdentification botIdentification) {
        this.botIdentification = botIdentification;
        return this;
    }
}
