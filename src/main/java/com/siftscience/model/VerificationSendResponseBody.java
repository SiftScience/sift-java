package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationSendResponseBody extends BaseResponseBody<VerificationSendResponseBody> {

    @Expose @SerializedName("sent_at") private Long sentAt;

    @Expose @SerializedName("segment_id") private String segmentId;

    @Expose @SerializedName("segment_name") private String segmentName;

    @Expose @SerializedName("brand_name") private String brandName;

    @Expose @SerializedName("site_country") private String siteCountry;

    @Expose @SerializedName("content_language") private String contentLanguage;


    public static VerificationSendResponseBody fromJson(String json) {
        return gson.fromJson(json, VerificationSendResponseBody.class);
    }


    public Long getSentAt() {
        return sentAt;
    }

    public VerificationSendResponseBody setSentAt(Long sentAt) {
        this.sentAt = sentAt;
        return this;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public VerificationSendResponseBody setSegmentId(String segmentId) {
        this.segmentId = segmentId;
        return this;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public VerificationSendResponseBody setSegmentName(String segmentName) {
        this.segmentName = segmentName;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public VerificationSendResponseBody setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getSiteCountry() {
        return siteCountry;
    }

    public VerificationSendResponseBody setSiteCountry(String siteCountry) {
        this.siteCountry = siteCountry;
        return this;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public VerificationSendResponseBody setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
        return this;
    }

}

