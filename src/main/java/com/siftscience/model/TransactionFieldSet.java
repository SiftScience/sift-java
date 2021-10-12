package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionFieldSet extends BaseAppBrowserSiteBrandFieldSet<TransactionFieldSet> {
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
    @Expose @SerializedName("$ordered_from") private OrderedFrom orderedFrom;
    @Expose @SerializedName("$decline_category") private String declineCategory;
    @Expose @SerializedName("$merchant_profile") private MerchantProfile merchantProfile;
    @Expose @SerializedName("$sent_address") private Address sentAddress;
    @Expose @SerializedName("$received_address") private Address receivedAddress;
    @Expose @SerializedName("$status_3ds") private String status3ds;
    @Expose @SerializedName("$triggered_3ds") private String triggered3ds;
    @Expose @SerializedName("$merchant_initiated_transaction")
        private Boolean merchantInitiatedTransaction;


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

    public OrderedFrom getOrderedFrom() {
        return orderedFrom;
    }

    public TransactionFieldSet setOrderedFrom(OrderedFrom orderedFrom) {
        this.orderedFrom = orderedFrom;
        return this;
    }

    public String getDeclineCategory() {
        return declineCategory;
    }

    public TransactionFieldSet setDeclineCategory(String declineCategory) {
        this.declineCategory = declineCategory;
        return this;
    }

    public MerchantProfile getMerchantProfile() {
        return merchantProfile;
    }

    public TransactionFieldSet setMerchantProfile(MerchantProfile merchantProfile) {
        this.merchantProfile = merchantProfile;
        return this;
    }

    public Address getSentAddress() {
        return sentAddress;
    }

    public TransactionFieldSet setSentAddress(Address sentAddress) {
        this.sentAddress = sentAddress;
        return this;
    }

    public Address getReceivedAddress() {
        return receivedAddress;
    }

    public TransactionFieldSet setReceivedAddress(Address receivedAddress) {
        this.receivedAddress = receivedAddress;
        return this;
    }

    public String getStatus3ds() {
        return status3ds;
    }

    public TransactionFieldSet setStatus3ds(String status3ds) {
        this.status3ds = status3ds;
        return this;
    }

    public String getTriggered3ds() {
        return triggered3ds;
    }

    public TransactionFieldSet setTriggered3ds(String triggered3ds) {
        this.triggered3ds = triggered3ds;
        return this;
    }

    public Boolean getMerchantInitiatedTransaction() {
        return merchantInitiatedTransaction;
    }

    public TransactionFieldSet setMerchantInitiatedTransaction(
            Boolean merchantInitiatedTransaction) {
        this.merchantInitiatedTransaction = merchantInitiatedTransaction;
        return this;
    }
}
