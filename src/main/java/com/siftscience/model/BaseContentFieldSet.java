package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

import java.util.List;

abstract class BaseContentFieldSet<T extends BaseContentFieldSet<T>> extends FieldSet<T> {

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$contact_email") private String contactEmail;
    @Expose @SerializedName("$contact_phone") private String contactPhone;
    @Expose @SerializedName("$subject") private String subject;
    @Expose @SerializedName("$content") private String content;
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$categories") private List<String> categories;
    @Expose @SerializedName("$locations") private List<Address> locations;
    @Expose @SerializedName("$image_hashes") private List<String> imageHashes;
    @Expose @SerializedName("$expiration_time") private Long expirationTime;
    @Expose @SerializedName("$status") private String status;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public T setUserId(String userId) {
        this.userId = userId;
        return (T) this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public T setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return (T) this;
    }

    public String getContentId() {
        return contentId;
    }

    public T setContentId(String contentId) {
        this.contentId = contentId;
        return (T) this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public T setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return (T) this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public T setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return (T) this;
    }

    public String getSubject() {
        return subject;
    }

    public T setSubject(String subject) {
        this.subject = subject;
        return (T) this;
    }

    public String getContent() {
        return content;
    }

    public T setContent(String content) {
        this.content = content;
        return (T) this;
    }

    public Long getAmount() {
        return amount;
    }

    public T setAmount(Long amount) {
        this.amount = amount;
        return (T) this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public T setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return (T) this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public T setCategories(List<String> categories) {
        this.categories = categories;
        return (T) this;
    }

    public List<Address> getLocations() {
        return locations;
    }

    public T setLocations(List<Address> locations) {
        this.locations = locations;
        return (T) this;
    }

    public List<String> getImageHashes() {
        return imageHashes;
    }

    public T setImageHashes(List<String> imageHashes) {
        this.imageHashes = imageHashes;
        return (T) this;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public T setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
        return (T) this;
    }

    public String getStatus() {
        return status;
    }

    public T setStatus(String status) {
        this.status = status;
        return (T) this;
    }

    public Integer getTime() {
        return time;
    }

    public BaseContentFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public BaseContentFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
