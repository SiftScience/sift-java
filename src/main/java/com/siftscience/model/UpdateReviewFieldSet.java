package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateReviewFieldSet extends UpdateContentFieldSet<UpdateReviewFieldSet> {
    public static UpdateReviewFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateReviewFieldSet.class);
    }

    @Expose @SerializedName("$review") private Review review;

    public Review getReview() {
        return review;
    }

    public UpdateReviewFieldSet setReview(Review review) {
        this.review = review;
        return this;
    }
}
