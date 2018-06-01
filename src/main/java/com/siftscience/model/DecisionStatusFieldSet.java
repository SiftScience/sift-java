package com.siftscience.model;

import com.siftscience.FieldSet;

public class DecisionStatusFieldSet extends FieldSet<DecisionStatusFieldSet> {

    public static final String ENTITY_USERS = "users";
    public static final String ENTITY_ORDERS = "orders";
    public static final String ENTITY_SESSION = "session";
    public static final String ENTITY_CONTENT = "content";

    private String accountId;
    private String entity;
    private String entityId;
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public DecisionStatusFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
