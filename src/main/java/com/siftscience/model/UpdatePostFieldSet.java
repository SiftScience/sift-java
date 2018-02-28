package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePostFieldSet extends UpdateContentFieldSet<UpdatePostFieldSet> {
    public static UpdatePostFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdatePostFieldSet.class);
    }

    @Expose @SerializedName("$post") private Post post;

    public Post getPost() {
        return post;
    }

    public UpdatePostFieldSet setPost(Post post) {
        this.post = post;
        return this;
    }
}
