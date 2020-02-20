package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class BaseAccountFieldSet<T extends BaseAccountFieldSet<T>>
        extends BaseAppBrowserSiteBrandFieldSet<T> {
    @Expose @SerializedName("$user_email") private String userEmail;
    @Expose @SerializedName("$name") private String name;
    @Expose @SerializedName("$phone") private String phone;
    @Expose @SerializedName("$referrer_user_id") private String referrerUserId;
    @Expose @SerializedName("$payment_methods") private List<PaymentMethod> paymentMethods;
    @Expose @SerializedName("$billing_address") private Address billingAddress;
    @Expose @SerializedName("$shipping_address") private Address shippingAddress;
    @Expose @SerializedName("$social_sign_on_type") private String socialSignOnType;

    public String getUserEmail() {
        return userEmail;
    }

    public T setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return (T) this;
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getPhone() {
        return phone;
    }

    public T setPhone(String phone) {
        this.phone = phone;
        return (T) this;
    }

    public String getReferrerUserId() {
        return referrerUserId;
    }

    public T setReferrerUserId(String referrerUserId) {
        this.referrerUserId = referrerUserId;
        return (T) this;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public T setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
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

    public String getSocialSignOnType() {
        return socialSignOnType;
    }

    public T setSocialSignOnType(String socialSignOnType) {
        this.socialSignOnType = socialSignOnType;
        return (T) this;
    }
}
