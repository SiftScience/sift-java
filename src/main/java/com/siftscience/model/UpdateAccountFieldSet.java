package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAccountFieldSet extends BaseAccountFieldSet<UpdateAccountFieldSet> {

    public static UpdateAccountFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateAccountFieldSet.class);
    }

    @Expose @SerializedName("$changed_password") private String changedPassword;

    @Override
    public String getEventType() {
        return "$update_account";
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public UpdateAccountFieldSet setChangedPassword(String changedPassword) {
        this.changedPassword = changedPassword;
        return this;
    }
}
