package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discount {
    @Expose @SerializedName("$percentage_off") private Double percentageOff;
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$amount_usd") private Long amountUsd;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$minimum_purchase_amount") private Long minimumPurchaseAmount;

    public Double getPercentageOff() {
        return percentageOff;
    }

    public Discount setPercentageOff(Double percentageOff) {
        this.percentageOff = percentageOff;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public Discount setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Discount setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Long getMinimumPurchaseAmount() {
        return minimumPurchaseAmount;
    }

    public Discount setMinimumPurchaseAmount(Long minimumPurchaseAmount) {
        this.minimumPurchaseAmount = minimumPurchaseAmount;
        return this;
    }

    public Long getAmountUsd() {
        return amountUsd;
    }

    public Discount setAmountUsd(Long amountUsd) {
        this.amountUsd = amountUsd;
        return this;
    }
}
