package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateAccountFieldSet extends BaseAccountFieldSet<CreateAccountFieldSet> {
    public static CreateAccountFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateAccountFieldSet.class);
    }

    @Expose @SerializedName("$account_types") private List<String> accountTypes;

    @Override
    public String getEventType() {
        return "$create_account";
    }

    public List<String> getAccountTypes() { return accountTypes; }

    public CreateAccountFieldSet setAccountTypes(List<String> accountTypes) {
        this.accountTypes = accountTypes;
        return this;
    }
}
