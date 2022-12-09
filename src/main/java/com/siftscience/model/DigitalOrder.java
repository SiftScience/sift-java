package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DigitalOrder {
    @Expose @SerializedName("$digital_asset") private String digitalAsset;
    @Expose @SerializedName("$pair") private String pair;
    @Expose @SerializedName("$asset_type") private String assetType;
    @Expose @SerializedName("$order_type") private String orderType;
    @Expose @SerializedName("$volume") private String volume;

    public String getDigitalAsset() {
        return digitalAsset;
    }

    public DigitalOrder setDigitalAsset(String digitalAsset) {
        this.digitalAsset = digitalAsset;
        return this;
    }

    public String getPair() {
        return pair;
    }

    public DigitalOrder setPair(String pair) {
        this.pair = pair;
        return this;
    }

    public String getAssetType() {
        return assetType;
    }

    public DigitalOrder setAssetType(String assetType) {
        this.assetType = assetType;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public DigitalOrder setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public String getVolume() {
        return volume;
    }

    public DigitalOrder setVolume(String volume) {
        this.volume = volume;
        return this;
    }
}
