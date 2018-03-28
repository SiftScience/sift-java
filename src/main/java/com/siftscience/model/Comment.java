package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @Expose @SerializedName("$body") private String body;
    @Expose @SerializedName("$contact_email") private String contactEmail;
    @Expose @SerializedName("$root_content_id") private String rootContentId;
    @Expose @SerializedName("$parent_comment_id") private String parentCommentId;
    @Expose @SerializedName("$images") private List<Image> images;

    public String getBody() {
        return body;
    }

    public Comment setBody(String body) {
        this.body = body;
        return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Comment setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Comment setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getRootContentId() {
        return rootContentId;
    }

    public Comment setRootContentId(String rootContentId) {
        this.rootContentId = rootContentId;
        return this;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public Comment setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
        return this;
    }
}
