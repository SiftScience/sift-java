package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantProfile {
    @Expose @SerializedName("$merchant_id") private String merchantId;
    @Expose @SerializedName("$merchant_category_code") private String merchantCategoryCode;
    @Expose @SerializedName("$merchant_name") private String merchantName;
    @Expose @SerializedName("$merchant_address") private Address merchantAddress;

    public String getMerchantId() {
        return merchantId;
    }

    public MerchantProfile setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public MerchantProfile setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
        return this;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public MerchantProfile setMerchantName(String merchantName) {
        this.merchantName = merchantName;
        return this;
    }

    public Address getMerchantAddress() {
        return merchantAddress;
    }

    public MerchantProfile setMerchantAddress(Address merchantAddress) {
        this.merchantAddress = merchantAddress;
        return this;
    }


}
