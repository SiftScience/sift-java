package com.siftscience.model;

import com.siftscience.FieldSet;;

public class GetMerchantsFieldSet extends FieldSet<GetMerchantsFieldSet> {
    private String batchToken;
    private String batchSize;

    public GetMerchantsFieldSet() {}

    public static GetMerchantsFieldSet fromJson(String json) {
        return gson.fromJson(json, GetMerchantsFieldSet.class);
    }

    public String getBatchToken() {
        return batchToken;
    }

    public GetMerchantsFieldSet setBatchToken(String batchToken) {
        this.batchToken = batchToken;
        return this;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public GetMerchantsFieldSet setBatchSize(String batchSize) {

        this.batchSize = batchSize;
        return this;
    }

}
