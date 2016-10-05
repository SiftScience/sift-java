package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promotion {
    @Expose @SerializedName("$promotion_id") private String promotionId;
    @Expose @SerializedName("$status") private String status;
    @Expose @SerializedName("$failure_reason") private String failureReason;
    @Expose @SerializedName("$description") private String description;
    @Expose @SerializedName("$referrer_user_id") private String referrerUserId;
    @Expose @SerializedName("$discount") private Discount discount;
    @Expose @SerializedName("$credit_point") private CreditPoint creditPoint;

    public String getPromotionId() {
        return promotionId;
    }

    public Promotion setPromotionId(String promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Promotion setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Promotion setFailureReason(String failureReason) {
        this.failureReason = failureReason;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Promotion setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReferrerUserId() {
        return referrerUserId;
    }

    public Promotion setReferrerUserId(String referrerUserId) {
        this.referrerUserId = referrerUserId;
        return this;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Promotion setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }

    public CreditPoint getCreditPoint() {
        return creditPoint;
    }

    public Promotion setCreditPoint(CreditPoint creditPoint) {
        this.creditPoint = creditPoint;
        return this;
    }
}
