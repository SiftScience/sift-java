package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @Expose @SerializedName("$subject") private String subject;
    @Expose @SerializedName("$body") private String body;
    @Expose @SerializedName("$contact_email") private String contactEmail;
    @Expose @SerializedName("$contact_address") private Address contactAddress;
    @Expose @SerializedName("$locations") private List<Address> locations;
    @Expose @SerializedName("$categories") private List<String> categories;
    @Expose @SerializedName("$images") private List<Image> images;
    @Expose @SerializedName("$expiration_time") private Long expirationTime;

    public String getBody() {
        return body;
    }

    public Post setBody(String body) {
        this.body = body;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Post setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Post setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public Post setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public List<Address> getLocations() {
        return locations;
    }

    public Post setLocations(List<Address> locations) {
        this.locations = locations;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Post setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public Post setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Post setSubject(String subject) {
        this.subject = subject;
        return this;
    }
}
