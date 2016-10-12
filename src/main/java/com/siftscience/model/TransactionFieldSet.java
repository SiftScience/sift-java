package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class TransactionFieldSet extends EventsApiRequestFieldSet<TransactionFieldSet> {

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
    public String getEventType() {
        return "$transaction";
    }

    public static TransactionFieldSet fromJson(String json) {
        return gson.fromJson(json, TransactionFieldSet.class);
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
}
