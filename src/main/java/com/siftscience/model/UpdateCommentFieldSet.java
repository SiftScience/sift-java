package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCommentFieldSet extends UpdateContentFieldSet<UpdateCommentFieldSet> {
    public static UpdateCommentFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateCommentFieldSet.class);
    }

    @Expose @SerializedName("$comment") private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public UpdateCommentFieldSet setComment(Comment comment) {
        this.comment = comment;
        return this;
    }
}
