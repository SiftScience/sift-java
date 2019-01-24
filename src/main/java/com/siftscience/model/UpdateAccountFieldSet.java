package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateAccountFieldSet extends BaseAccountFieldSet<UpdateAccountFieldSet> {
    public static UpdateAccountFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateAccountFieldSet.class);
    }

    @Expose @SerializedName("$account_types") private List<String> accountTypes;
    @Expose @SerializedName("$changed_password") private Boolean changedPassword;

    @Override
    public String getEventType() {
        return "$update_account";
    }

    public Boolean getChangedPassword() {
        return changedPassword;
    }

    public UpdateAccountFieldSet setChangedPassword(Boolean changedPassword) {
        this.changedPassword = changedPassword;
        return this;
    }

    public List<String> getAccountTypes() { return accountTypes; }

    public UpdateAccountFieldSet setAccountTypes(List<String> accountTypes) {
        this.accountTypes = accountTypes;
        return this;
    }
}
