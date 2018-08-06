package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

import java.util.List;

public class UserScoreFieldSet extends FieldSet<UserScoreFieldSet> {
    @Expose @SerializedName(USER_ID) private String userId;
    private List<String> abuseTypes;
    private boolean rescoreUser = false;

    public String getUserId() {
        return userId;
    }

    public UserScoreFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public List<String> getAbuseTypes() {
        return abuseTypes;
    }

    public UserScoreFieldSet setAbuseTypes(List<String> abuseTypes) {
        this.abuseTypes = abuseTypes;
        return this;
    }

    public boolean getRescoreUser() {
        return rescoreUser;
    }

    public UserScoreFieldSet setRescoreUser(boolean rescoreUser) {
        this.rescoreUser = rescoreUser;
        return this;
    }

}
