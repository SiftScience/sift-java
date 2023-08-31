package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class VerificationSendFieldSet extends FieldSet<VerificationSendFieldSet> {
    public static VerificationSendFieldSet fromJson(String json) {
        return gson.fromJson(json, VerificationSendFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName("$send_to") private String sendTo;
    @Expose @SerializedName("$verification_type") private String verificationType;
    @Expose @SerializedName("$brand_name") private String brandName;
    @Expose @SerializedName("$site_country") private String siteCountry;
    @Expose @SerializedName("$language") private String language;
    @Expose @SerializedName("$event") private Event verificationEvent;

    public String getUserId() {
        return userId;
    }

    public VerificationSendFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSendTo() {
        return sendTo;
    }

    public VerificationSendFieldSet setSendTo(String sendTo) {
        this.sendTo = sendTo;
        return this;
    }

    public String getVerificationType() {
        return verificationType;
    }

    public VerificationSendFieldSet setVerificationType(String verificationType) {
        this.verificationType = verificationType;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public VerificationSendFieldSet setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getSiteCountry() {
        return siteCountry;
    }

    public VerificationSendFieldSet setSiteCountry(String siteCountry) {
        this.siteCountry = siteCountry;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public VerificationSendFieldSet setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Event getVerificationEvent() {
        return verificationEvent;
    }

    public VerificationSendFieldSet setVerificationEvent(Event verificationEvent) {
        this.verificationEvent = verificationEvent;
        return this;
    }

}
