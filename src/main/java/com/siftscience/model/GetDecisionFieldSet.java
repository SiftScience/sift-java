package com.siftscience.model;

import com.siftscience.FieldSet;

import java.util.List;

public class GetDecisionFieldSet extends FieldSet<GetDecisionFieldSet> {
    public GetDecisionFieldSet() {}

    public static GetDecisionFieldSet fromJson(String json) {
        return gson.fromJson(json, GetDecisionFieldSet.class);
    }

    private String accountId;
    private Integer limit;
    private Long createdBefore;
    private EntityType entityType;
    private List<AbuseType> abuseTypes;

    public enum AbuseType {
        PAYMENT_ABUSE, CONTENT_ABUSE, PROMOTION_ABUSE, ACCOUNT_ABUSE, LEGACY, ACCOUNT_TAKEOVER
    }
    public enum EntityType {USER, ORDER}
    public enum DecisionCategory {BLOCK, WATCH, ACCEPT}

    public GetDecisionFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GetDecisionFieldSet setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetDecisionFieldSet setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }

    public GetDecisionFieldSet setEntityType(EntityType entityType) {
        this.entityType = entityType;
        return this;
    }

    public GetDecisionFieldSet setAbuseTypes(List<AbuseType> abuseTypes) {
        this.abuseTypes = abuseTypes;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public Integer getLimit() {
        return limit;
    }

    public Long getCreatedBefore() {
        return createdBefore;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public List<AbuseType> getAbuseTypes() {
        return abuseTypes;
    }
}
