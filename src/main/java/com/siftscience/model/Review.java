package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
    @Expose @SerializedName("$subject") private String subject;
    @Expose @SerializedName("$body") private String body;
    @Expose @SerializedName("$contact_email") private String contactEmail;
    @Expose @SerializedName("$locations") private List<Address> locations;
    @Expose @SerializedName("$reviewed_content_id") private String reviewedContentId;
    @Expose @SerializedName("$images") private List<Image> images;
    @Expose @SerializedName("$rating") private Double rating;

    public String getBody() {
        return body;
    }

    public Review setBody(String body) {
        this.body = body;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Review setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Review setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public List<Address> getLocations() {
        return locations;
    }

    public Review setLocations(List<Address> locations) {
        this.locations = locations;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Review setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Review setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getReviewedContentId() {
        return reviewedContentId;
    }

    public Review setReviewedContentId(String reviewedContentId) {
        this.reviewedContentId = reviewedContentId;
        return this;
    }
}
