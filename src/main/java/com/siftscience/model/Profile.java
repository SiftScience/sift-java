package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @Expose @SerializedName("$body") private String body;
    @Expose @SerializedName("$contact_email") private String contactEmail;
    @Expose @SerializedName("$contact_address") private Address contactAddress;
    @Expose @SerializedName("$categories") private List<String> categories;
    @Expose @SerializedName("$images") private List<Image> images;
    @Expose @SerializedName("$expiration_time") private Long expirationTime;

    public String getBody() {
        return body;
    }

    public Profile setBody(String body) {
        this.body = body;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Profile setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Profile setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public Profile setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Profile setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public Profile setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }
}
