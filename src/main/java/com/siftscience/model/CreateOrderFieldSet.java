package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateOrderFieldSet extends FieldSet<CreateOrderFieldSet> {

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

    public String getUserId() {
        return userId;
    }

    public CreateOrderFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getEventType() {
        return "$create_order";
    }

    public static CreateOrderFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateOrderFieldSet.class);
    }

    public String getSessionId() {
        return sessionId;
    }

    public CreateOrderFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public CreateOrderFieldSet setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public CreateOrderFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public CreateOrderFieldSet setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CreateOrderFieldSet setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Boolean getExpeditedShipping() {
        return expeditedShipping;
    }

    public CreateOrderFieldSet setExpeditedShipping(Boolean expeditedShipping) {
        this.expeditedShipping = expeditedShipping;
        return this;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public CreateOrderFieldSet setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public CreateOrderFieldSet setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public CreateOrderFieldSet setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
        return this;
    }

    public Boolean isExpeditedShipping() {
        return expeditedShipping;
    }

    public List<Item> getItems() {
        return items;
    }

    public CreateOrderFieldSet setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public CreateOrderFieldSet setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
        return this;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public CreateOrderFieldSet setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public CreateOrderFieldSet setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }
}
