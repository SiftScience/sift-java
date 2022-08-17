package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddPromotionFieldSet extends BaseAppBrowserSiteBrandFieldSet<AddPromotionFieldSet> {
    public static AddPromotionFieldSet fromJson(String json) {
        return gson.fromJson(json, AddPromotionFieldSet.class);
    }

    @Expose @SerializedName("$promotions") private List<Promotion> promotions;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    @Override
    public String getEventType() {
        return "$add_promotion";
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public AddPromotionFieldSet setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public AddPromotionFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }
}
