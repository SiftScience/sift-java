package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class BaseOrderFieldSet<T extends BaseOrderFieldSet<T>> extends FieldSet<T> {

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName("$user_email") private String userEmail;
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$billing_address") private Address billingAddress;
    @Expose @SerializedName("$shipping_address") private Address shippingAddress;
    @Expose @SerializedName("$payment_methods") private List<PaymentMethod> paymentMethods;
    @Expose @SerializedName("$expedited_shipping") private Boolean expeditedShipping;
    @Expose @SerializedName("$items") private List<Item> items;
    @Expose @SerializedName("$seller_user_id") private String sellerUserId;
    @Expose @SerializedName("$promotions") private List<Promotion> promotions;
    @Expose @SerializedName("$shipping_method") private String shippingMethod;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public T setUserId(String userId) {
        this.userId = userId;
        return (T) this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public T setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return (T) this;
    }

    public String getOrderId() {
        return orderId;
    }

    public T setOrderId(String orderId) {
        this.orderId = orderId;
        return (T) this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public T setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return (T) this;
    }

    public Long getAmount() {
        return amount;
    }

    public T setAmount(Long amount) {
        this.amount = amount;
        return (T) this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public T setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return (T) this;
    }

    public Boolean getExpeditedShipping() {
        return expeditedShipping;
    }

    public T setExpeditedShipping(Boolean expeditedShipping) {
        this.expeditedShipping = expeditedShipping;
        return (T) this;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public T setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return (T) this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public T setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return (T) this;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public T setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
        return (T) this;
    }

    public Boolean isExpeditedShipping() {
        return expeditedShipping;
    }

    public List<Item> getItems() {
        return items;
    }

    public T setItems(List<Item> items) {
        this.items = items;
        return (T) this;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public T setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
        return (T) this;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public T setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return (T) this;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public T setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return (T) this;
    }
}
