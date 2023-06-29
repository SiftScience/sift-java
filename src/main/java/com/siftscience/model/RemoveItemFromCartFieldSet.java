package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveItemFromCartFieldSet extends BaseAppBrowserSiteBrandFieldSet<RemoveItemFromCartFieldSet> {
    public static RemoveItemFromCartFieldSet fromJson(String json) {
        return gson.fromJson(json, RemoveItemFromCartFieldSet.class);
    }

    @Expose @SerializedName("$item") private Item item;
    @Expose @SerializedName(USER_EMAIL) private String userEmail;
    @Expose @SerializedName(VERIFICATION_PHONE_NUMBER) private String verificationPhoneNumber;

    @Override
    public String getEventType() {
        return "$remove_item_from_cart";
    }

    public Item getItem() {
        return item;
    }

    public RemoveItemFromCartFieldSet setItem(Item item) {
        this.item = item;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public RemoveItemFromCartFieldSet setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getVerificationPhoneNumber() {
        return verificationPhoneNumber;
    }

    public RemoveItemFromCartFieldSet setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }
}
