package com.siftscience.model;

import com.siftscience.FieldSet;

public class GetDecisionFieldSet extends FieldSet<GetDecisionFieldSet> {
    public GetDecisionFieldSet() {}

    public static GetDecisionFieldSet fromJson(String json) {
        return gson.fromJson(json, GetDecisionFieldSet.class);
    }

    public static final String ENTITY_USERS = "users";
    public static final String ENTITY_ORDERS = "orders";

    private String accountId;
    private String entity;

    public GetDecisionFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GetDecisionFieldSet setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getEntity() {
        return entity;
    }
}
