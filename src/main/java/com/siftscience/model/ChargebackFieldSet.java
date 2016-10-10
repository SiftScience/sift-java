package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class ChargebackFieldSet extends FieldSet<ChargebackFieldSet> {

    public static ChargebackFieldSet fromJson(String json) {
        return gson.fromJson(json, ChargebackFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName("$transaction_id") private String transactionId;
    @Expose @SerializedName("$chargeback_state") private String chargebackState;
    @Expose @SerializedName("$chargeback_reason") private String chargebackReason;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$chargeback";
    }

    @Override
    public void validate() {
        super.validate();
        validateStringField("$order_id", getOrderId());
        validateStringField("$transaction_id", getTransactionId());
    }

    public String getUserId() {
        return userId;
    }

    public ChargebackFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ChargebackFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
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

    public Integer getTime() {
        return time;
    }

    public ChargebackFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public ChargebackFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
