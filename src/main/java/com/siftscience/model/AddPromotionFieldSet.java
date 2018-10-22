package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddPromotionFieldSet extends BaseAppBrowserFieldSet<AddPromotionFieldSet> {
    public static AddPromotionFieldSet fromJson(String json) {
        return gson.fromJson(json, AddPromotionFieldSet.class);
    }

    @Expose @SerializedName("$promotions") private List<Promotion> promotions;

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
}
