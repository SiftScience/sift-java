package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveItemFromCartFieldSet extends BaseAppBrowserFieldSet<RemoveItemFromCartFieldSet> {
    public static RemoveItemFromCartFieldSet fromJson(String json) {
        return gson.fromJson(json, RemoveItemFromCartFieldSet.class);
    }

    @Expose @SerializedName("$item") private Item item;

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
}
