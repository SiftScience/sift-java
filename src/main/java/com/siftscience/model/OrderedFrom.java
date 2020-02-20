package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderedFrom {
    @Expose @SerializedName("$store_address") private Address storeAddress;
    @Expose @SerializedName("$store_id") private String storeId;

    public Address getStoreAddress() {
        return storeAddress;
    }

    public OrderedFrom setStoreAddress(Address storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public OrderedFrom setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }
}
