package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddItemToCartFieldSet extends BaseAppBrowserSiteBrandFieldSet<AddItemToCartFieldSet> {
    public static AddItemToCartFieldSet fromJson(String json) {
        return gson.fromJson(json, AddItemToCartFieldSet.class);
    }

    @Expose @SerializedName("$item") private Item item;
    @Expose @SerializedName("$user_email") private String userEmail;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    @Override
    public String getEventType() {
        return "$add_item_to_cart";
    }

    public Item getItem() {
        return item;
    }

    public AddItemToCartFieldSet setItem(Item item) {
        this.item = item;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public AddItemToCartFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public AddItemToCartFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }
}
