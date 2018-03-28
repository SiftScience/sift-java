package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateReviewFieldSet extends CreateContentFieldSet<CreateReviewFieldSet> {
    public static CreateReviewFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateReviewFieldSet.class);
    }

    @Expose @SerializedName("$review") private Review review;

    public Review getReview() {
        return review;
    }

    public CreateReviewFieldSet setReview(Review review) {
        this.review = review;
        return this;
    }
}
