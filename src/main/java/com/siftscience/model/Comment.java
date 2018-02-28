package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @Expose
    @SerializedName("$root_comment_id") private String rootCommentId;
    @Expose @SerializedName("$parent_comment_id") private String parentCommentId;
    @Expose @SerializedName("$images") private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public Comment setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getRootCommentId() {
        return rootCommentId;
    }

    public Comment setRootCommentId(String rootCommentId) {
        this.rootCommentId = rootCommentId;
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
