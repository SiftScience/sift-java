package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditPoint {
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$credit_point_type") private String creditPointType;

    public Long getAmount() {
        return amount;
    }

    public CreditPoint setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public String getCreditPointType() {
        return creditPointType;
    }

    public CreditPoint setCreditPointType(String creditPointType) {
        this.creditPointType = creditPointType;
        return this;
    }
}
