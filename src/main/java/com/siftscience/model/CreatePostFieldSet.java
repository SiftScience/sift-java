package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostFieldSet extends CreateContentFieldSet<CreatePostFieldSet> {
    public static CreatePostFieldSet fromJson(String json) {
        return gson.fromJson(json, CreatePostFieldSet.class);
    }

    @Expose @SerializedName("$post") private Post post;

    public Post getPost() {
        return post;
    }

    public CreatePostFieldSet setPost(Post post) {
        this.post = post;
        return this;
    }
}
