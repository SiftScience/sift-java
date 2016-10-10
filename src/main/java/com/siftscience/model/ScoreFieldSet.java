package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

import java.util.List;

public class ScoreFieldSet extends FieldSet<ScoreFieldSet> {

    @Expose @SerializedName(USER_ID) private String userId;
    private List<String> abuseTypes;

    @Override
    protected boolean allowCustomFields() {
        return false;
    }

    @Override
    public String getEventType() {
        return null;
    }

    public String getUserId() {
        return userId;
    }

    public ScoreFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public List<String> getAbuseTypes() {
        return abuseTypes;
    }

    public ScoreFieldSet setAbuseTypes(List<String> abuseTypes) {
        this.abuseTypes = abuseTypes;
        return this;
    }

}
