package com.siftscience.model;

public enum MerchantRiskLevelEnum {

    LOW("low"),
    HIGH("high"),
    Medium("medium");

    public final String value;

    MerchantRiskLevelEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
