package com.siftscience.model;

import com.siftscience.FieldSet;

public class GetMerchantFieldSet extends FieldSet<GetMerchantFieldSet> {
    private String merchantId;

    public GetMerchantFieldSet() {}

    public static GetMerchantFieldSet fromJson(String json) {
        return gson.fromJson(json, GetMerchantFieldSet.class);
    }

    public String getMerchantId() {
        return merchantId;
    }

    public GetMerchantFieldSet setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }
}
