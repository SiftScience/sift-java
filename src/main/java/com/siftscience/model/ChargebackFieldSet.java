package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChargebackFieldSet extends EventsApiRequestFieldSet<ChargebackFieldSet> {
    public static ChargebackFieldSet fromJson(String json) {
        return gson.fromJson(json, ChargebackFieldSet.class);
    }

    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName("$transaction_id") private String transactionId;
    @Expose @SerializedName("$chargeback_state") private String chargebackState;
    @Expose @SerializedName("$chargeback_reason") private String chargebackReason;
    @Expose @SerializedName("$merchant_profile") private MerchantProfile merchantProfile;

    @Override
    public String getEventType() {
        return "$chargeback";
    }

    public String getOrderId() {
        return orderId;
    }

    public ChargebackFieldSet setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public ChargebackFieldSet setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getChargebackState() {
        return chargebackState;
    }

    public ChargebackFieldSet setChargebackState(String chargebackState) {
        this.chargebackState = chargebackState;
        return this;
    }

    public String getChargebackReason() {
        return chargebackReason;
    }

    public ChargebackFieldSet setChargebackReason(String chargebackReason) {
        this.chargebackReason = chargebackReason;
        return this;
    }

    public MerchantProfile getMerchantProfile() {
        return merchantProfile;
    }

    public ChargebackFieldSet setMerchantProfile(MerchantProfile merchantProfile) {
        this.merchantProfile = merchantProfile;
        return this;
    }
}
