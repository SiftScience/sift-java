package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantAddress {
    @Expose @SerializedName("name") private String name;
    @Expose @SerializedName("phone") private String phone;
    @Expose @SerializedName("address_1") private String address1;
    @Expose @SerializedName("address_2") private String address2;
    @Expose @SerializedName("city") private String city;
    @Expose @SerializedName("region") private String region;
    @Expose @SerializedName("country") private String country;
    @Expose @SerializedName("zipcode") private String zipCode;

    public String getName() {
        return name;
    }

    public MerchantAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MerchantAddress setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public MerchantAddress setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public MerchantAddress setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public MerchantAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public MerchantAddress setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public MerchantAddress setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public MerchantAddress setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
}
