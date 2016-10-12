package com.siftscience.model;

import com.siftscience.FieldSet;

public class UnlabelFieldSet extends FieldSet<UnlabelFieldSet> {

    private String userId;
    private String abuseType;

    public String getUserId() {
        return userId;
    }

    public UnlabelFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getAbuseType() {
        return abuseType;
    }

    public UnlabelFieldSet setAbuseType(String abuseType) {
        this.abuseType = abuseType;
        return this;
    }
}
