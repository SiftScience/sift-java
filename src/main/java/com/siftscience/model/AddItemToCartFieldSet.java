package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddItemToCartFieldSet extends BaseAppBrowserFieldSet<AddItemToCartFieldSet> {
    public static AddItemToCartFieldSet fromJson(String json) {
        return gson.fromJson(json, AddItemToCartFieldSet.class);
    }

    @Expose @SerializedName("$item") private Item item;

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
}
