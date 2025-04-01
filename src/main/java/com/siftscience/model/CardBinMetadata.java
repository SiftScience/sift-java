package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardBinMetadata {
    @Expose @SerializedName("$bank") private String bank;
    @Expose @SerializedName("$brand") private String brand;
    @Expose @SerializedName("$country") private String country;
    @Expose @SerializedName("$level") private String level;
    @Expose @SerializedName("$type") private String type;

    public String getBank() {
        return bank;
    }

    public CardBinMetadata setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CardBinMetadata setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CardBinMetadata setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public CardBinMetadata setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getType() {
        return type;
    }

    public CardBinMetadata setType(String type) {
        this.type = type;
        return this;
    }
}
