package com.siftscience.model;

public enum MerchantStatusEnum {
    ACTIVE("active"),
    CHURNED("churned"),
    INACTIVE("inactive"),
    PAUSED("paused");

    public final String value;

    MerchantStatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
