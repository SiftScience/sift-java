package com.siftscience.model;

import com.siftscience.FieldSet;

public class DecisionStatusFieldSet extends FieldSet<DecisionStatusFieldSet> {

    public static final String ENTITY_USERS = "users";
    public static final String ENTITY_ORDERS = "orders";
    public static final String ENTITY_CONTENT = "content";

    private String accountId;
    private String entity;
    private String entityId;

    public String getAccountId() {
        return accountId;
    }

    public DecisionStatusFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public DecisionStatusFieldSet setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getEntityId() {
        return entityId;
    }

    public DecisionStatusFieldSet setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }
}
