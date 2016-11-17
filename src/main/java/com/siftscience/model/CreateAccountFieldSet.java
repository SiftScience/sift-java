package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateAccountFieldSet extends BaseAccountFieldSet<CreateAccountFieldSet> {
    public static CreateAccountFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateAccountFieldSet.class);
    }

    @Expose @SerializedName("$promotions") private List<Promotion> promotions;

    @Override
    public String getEventType() {
        return "$create_account";
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public CreateAccountFieldSet setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }
}
