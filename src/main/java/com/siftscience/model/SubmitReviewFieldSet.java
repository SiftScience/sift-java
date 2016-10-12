package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class SubmitReviewFieldSet extends EventsApiRequestFieldSet<SubmitReviewFieldSet> {

    public static SubmitReviewFieldSet fromJson(String json) {
        return gson.fromJson(json, SubmitReviewFieldSet.class);
    }

    @Expose @SerializedName("$content") private String content;
    @Expose @SerializedName("$review_title") private String reviewTitle;
    @Expose @SerializedName("$item_id") private String itemId;
    @Expose @SerializedName("$reviewed_user_id") private String reviewedUserId;
    @Expose @SerializedName("$submission_status") private String submissionStatus;

    @Override
    public String getEventType() {
        return "$submit_review";
    }

    public String getContent() {
        return content;
    }

    public SubmitReviewFieldSet setContent(String content) {
        this.content = content;
        return this;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public SubmitReviewFieldSet setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public SubmitReviewFieldSet setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getReviewedUserId() {
        return reviewedUserId;
    }

    public SubmitReviewFieldSet setReviewedUserId(String reviewedUserId) {
        this.reviewedUserId = reviewedUserId;
        return this;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public SubmitReviewFieldSet setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
        return this;
    }
}
