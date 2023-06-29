package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class BaseOrderFieldSet<T extends BaseOrderFieldSet<T>>
        extends BaseAppBrowserSiteBrandFieldSet<T> {
    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName(USER_EMAIL) private String userEmail;
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
    @Expose @SerializedName("shipping_carrier") private String shippingCarrier;
    @Expose @SerializedName("shipping_tracking_numbers") private List<String> shippingTrackingNumbers;
    @Expose @SerializedName("$bookings") private List<Booking> bookings;
    @Expose @SerializedName("$ordered_from") private OrderedFrom orderedFrom;
    @Expose @SerializedName("$merchant_profile") private MerchantProfile merchantProfile;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;
    @Expose @SerializedName("$digital_orders") private List<DigitalOrder> digitalOrders;

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public T setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public String getShippingCarrier() {
        return shippingCarrier;
    }

    public T setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
        return (T) this;
    }

    public List<String> getShippingTrackingNumbers() {
        return shippingTrackingNumbers;
    }

    public T setShippingTrackingNumbers(List<String> shippingTrackingNumbers) {
        this.shippingTrackingNumbers = shippingTrackingNumbers;
        return (T) this;
    }

    public OrderedFrom getOrderedFrom() {
        return orderedFrom;
    }

    public T setOrderedFrom(OrderedFrom orderedFrom) {
        this.orderedFrom = orderedFrom;
        return (T) this;
    }

    public MerchantProfile getMerchantProfile() {
        return merchantProfile;
    }

    public T setMerchantProfile(MerchantProfile merchantProfile) {
        this.merchantProfile = merchantProfile;
        return (T) this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public T setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return (T) this;
    }

    public List<DigitalOrder> getDigitalOrders() {
        return digitalOrders;
    }

    public T setDigitalOrders(List<DigitalOrder> digitalOrders) {
        this.digitalOrders = digitalOrders;
        return (T) this;
    }
}
