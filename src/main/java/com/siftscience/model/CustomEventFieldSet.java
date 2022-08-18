package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomEventFieldSet extends BaseAppBrowserSiteBrandFieldSet<CustomEventFieldSet> {
    public static CustomEventFieldSet fromJson(String json) {
        return gson.fromJson(json, CustomEventFieldSet.class);
    }

    @Expose @SerializedName(EVENT_TYPE) private String eventType;
    @Expose @SerializedName("$user_email") private String userEmail;
    @Expose @SerializedName("$name") private String name;
    @Expose @SerializedName("$phone") private String phone;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

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

    public String getName() {
        return name;
    }

    public CustomEventFieldSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomEventFieldSet setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public CustomEventFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }
}
