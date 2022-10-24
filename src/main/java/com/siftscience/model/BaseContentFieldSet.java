package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseContentFieldSet<T extends BaseContentFieldSet<T>>
        extends BaseAppBrowserSiteBrandFieldSet<T> {
    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$status") private String status;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    public String getContentId() {
        return contentId;
    }

    public T setContentId(String contentId) {
        this.contentId = contentId;
        return (T) this;
    }

    public String getStatus() {
        return status;
    }

    public T setStatus(String status) {
        this.status = status;
        return (T) this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public T setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return (T) this;
    }
}
