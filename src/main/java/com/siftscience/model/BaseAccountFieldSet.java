package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class BaseAccountFieldSet<T extends BaseAccountFieldSet<T>>
        extends BaseAppBrowserSiteBrandFieldSet<T> {
    @Expose @SerializedName(USER_EMAIL) private String userEmail;
    @Expose @SerializedName("$name") private String name;
    @Expose @SerializedName("$phone") private String phone;
    @Expose @SerializedName("$referrer_user_id") private String referrerUserId;
    @Expose @SerializedName("$payment_methods") private List<PaymentMethod> paymentMethods;
    @Expose @SerializedName("$billing_address") private Address billingAddress;
    @Expose @SerializedName("$shipping_address") private Address shippingAddress;
    @Expose @SerializedName("$promotions") private List<Promotion> promotions;
    @Expose @SerializedName("$social_sign_on_type") private String socialSignOnType;
    @Expose @SerializedName("$merchant_profile") private MerchantProfile merchantProfile;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;
    @Expose @SerializedName("$nationality") private String nationality;
    @Expose @SerializedName("$year_of_birth") private Integer yearOfBirth;
    @Expose @SerializedName("$kyc") private Kyc kyc;
    @Expose @SerializedName("$geo") private Geo geo;
    @Expose @SerializedName("$bot_identification") private BotIdentification botIdentification;

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

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public T setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return (T) this;
    }

    public String getNationality() {
        return nationality;
    }

    public T setNationality(String nationality) {
        this.nationality = nationality;
        return (T) this;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public T setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return (T) this;
    }

    public Kyc getKyc() {
        return kyc;
    }

    public T setKyc(Kyc kyc) {
        this.kyc = kyc;
        return (T) this;
    }

    public Geo getGeo() {
        return geo;
    }

    public T setGeo(Geo geo) {
        this.geo = geo;
        return (T) this;
    }

    public BotIdentification getBotIdentification() {
        return botIdentification;
    }

    public T setBotIdentification(BotIdentification botIdentification) {
        this.botIdentification = botIdentification;
        return (T) this;
    }
}
