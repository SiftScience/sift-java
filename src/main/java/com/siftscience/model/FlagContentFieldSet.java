package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlagContentFieldSet extends EventsApiRequestFieldSet<FlagContentFieldSet> {
    public static FlagContentFieldSet fromJson(String json) {
        return gson.fromJson(json, FlagContentFieldSet.class);
    }

    public enum FlagContentReason {
        TOXIC("$toxic"),
        IRRELEVANT("$irrelevant"),
        COMMERCIAL("$commercial"),
        PHISHING("$phishing"),
        PRIVATE("$private"),
        SCAM("$scam"),
        COPYRIGHT("$copyright"),
        OTHER("$other");

        public final String value;

        FlagContentReason(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Expose @SerializedName("$brand_name") private String brandName;
    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$flagged_by") private String flaggedBy;
    @Expose @SerializedName("$reason") private String reason;
    @Expose @SerializedName("$site_country") private String siteCountry;
    @Expose @SerializedName("$site_domain") private String siteDomain;
    @Expose @SerializedName(USER_EMAIL) private String userEmail;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    @Override
    public String getEventType() {
        return "$flag_content";
    }

    public String getContentId() {
        return contentId;
    }

    public FlagContentFieldSet setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getFlaggedBy() {
        return flaggedBy;
    }

    public FlagContentFieldSet setFlaggedBy(String flaggedBy) {
        this.flaggedBy = flaggedBy;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public FlagContentFieldSet setReason(FlagContentReason reason) {
        this.reason = reason.value;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public FlagContentFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public FlagContentFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }

    public String getSiteDomain() {
        return siteDomain;
    }

    public FlagContentFieldSet setSiteDomain(String siteDomain) {
        this.siteDomain = siteDomain;
        return this;
    }

    public String getSiteCountry() {
        return siteCountry;
    }

    public FlagContentFieldSet setSiteCountry(String siteCountry) {
        this.siteCountry = siteCountry;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public FlagContentFieldSet setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }
}
