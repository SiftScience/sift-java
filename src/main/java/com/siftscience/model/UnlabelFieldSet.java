package com.siftscience.model;

import com.siftscience.FieldSet;

public class UnlabelFieldSet extends FieldSet<UnlabelFieldSet> {

    private String userId;
    private String abuseType;

    @Override
    protected boolean allowCustomFields() {
        return false;
    }

    @Override
    public String getEventType() {
        return null;
    }

    @Override
    public void validate() {
        validateApiKey();
        validateStringField(USER_ID, userId);
    }

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
