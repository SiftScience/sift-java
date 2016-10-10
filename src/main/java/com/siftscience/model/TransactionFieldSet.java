package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class TransactionFieldSet extends FieldSet<TransactionFieldSet> {

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$user_email") private String userEmail;
    @Expose @SerializedName("$transaction_type") private String transactionType;
    @Expose @SerializedName("$transaction_status") private String transactionStatus;
    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName("$transaction_id") private String transactionId;
    @Expose @SerializedName("$billing_address") private Address billingAddress;
    @Expose @SerializedName("$payment_method") private PaymentMethod paymentMethod;
    @Expose @SerializedName("$shipping_address") private Address shippingAddress;
    @Expose @SerializedName("$seller_user_id") private String sellerUserId;
    @Expose @SerializedName("$transfer_recipient_user_id") private String transferRecipientUserId;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$transaction";
    }

    @Override
    public void validate() {
        super.validate();
        validateLongField("$amount", getAmount());
        validateStringField("$currency_code", getCurrencyCode());
    }

    public static TransactionFieldSet fromJson(String json) {
        return gson.fromJson(json, TransactionFieldSet.class);
    }

    public String getUserId() {
        return userId;
    }

    public TransactionFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public TransactionFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public TransactionFieldSet setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public TransactionFieldSet setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public TransactionFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public TransactionFieldSet setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public TransactionFieldSet setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public TransactionFieldSet setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionFieldSet setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public TransactionFieldSet setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TransactionFieldSet setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public TransactionFieldSet setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public TransactionFieldSet setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
        return this;
    }

    public String getTransferRecipientUserId() {
        return transferRecipientUserId;
    }

    public TransactionFieldSet setTransferRecipientUserId(String transferRecipientUserId) {
        this.transferRecipientUserId = transferRecipientUserId;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public TransactionFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public TransactionFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
