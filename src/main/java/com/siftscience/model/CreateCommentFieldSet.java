package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCommentFieldSet extends CreateContentFieldSet<CreateCommentFieldSet> {
    public static CreateContentFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateContentFieldSet.class);
    }

    @Expose @SerializedName("$comment") private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public CreateCommentFieldSet setComment(Comment comment) {
        this.comment = comment;
        return this;
    }
}
