package com.siftscience.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.siftscience.FieldSet;
import com.siftscience.GetDecisionsRequest;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.exception.InvalidRequestException;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDecisionFieldSet extends FieldSet<GetDecisionFieldSet> {
    private String accountId;
    private Integer limit;
    private Integer from;
    private EntityType entityType;
    private List<AbuseType> abuseTypes;
    private static final Pattern ACCOUNT_ID_PATTERN = Pattern
            .compile("(?<=accounts/)\\p{XDigit}+[^/]");

    public GetDecisionFieldSet() {}

    public static GetDecisionFieldSet fromJson(String json) {
        return gson.fromJson(json, GetDecisionFieldSet.class);
    }

    public static GetDecisionFieldSet fromNextRef(String nextRef) {
        Preconditions.checkNotNull(nextRef,"Must provide valid nextRef");
        URI uri = URI.create(nextRef);
        String queries = uri.getQuery();
            if ( queries == null || queries.isEmpty()) {
            throw new InvalidRequestException("Invalid format for nextRef " + nextRef);
        } else {
            GetDecisionFieldSet fieldSet = new GetDecisionFieldSet();
                Matcher matcher = ACCOUNT_ID_PATTERN.matcher(uri.getPath());
                if (matcher.find()) {
                    fieldSet.setAccountId(matcher.group());
                } else {
                    throw new InvalidFieldException("Unable to parse accountId from ref " +
                            nextRef);
                }
            for (String query : queries.split("&")) {
                String[] pair = query.split("=");
                if (pair.length == 2) {
                    switch (GetDecisionsRequest.Query.valueOf(pair[0].toUpperCase())) {
                        case ABUSE_TYPES:
                            fieldSet.setAbuseTypes(pair[1]);
                            break;
                        case ENTITY_TYPE:
                            fieldSet.setEntityType(EntityType.valueOf(pair[1].toUpperCase()));
                            break;
                        case FROM:
                            fieldSet.setFrom(Integer.valueOf(pair[1]));
                            break;
                        case LIMIT:
                            fieldSet.setLimit(Integer.valueOf(pair[1]));
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new InvalidFieldException("Invalid query " + query);
                }
            }
            return fieldSet;
        }
    }

    private void setAbuseTypes(String abuseTypeCsv) {
        ImmutableList.Builder<AbuseType> abuseTypes = ImmutableList.builder();
        for (String abuseType : abuseTypeCsv.split(",")) {
            abuseTypes.add(AbuseType.valueOf(abuseType.toUpperCase()));
        }
        this.abuseTypes = abuseTypes.build();
    }

    public enum AbuseType {
        PAYMENT_ABUSE, CONTENT_ABUSE, PROMOTION_ABUSE, ACCOUNT_ABUSE, LEGACY, ACCOUNT_TAKEOVER
    }
    public enum EntityType {USER, ORDER, SESSION}
    public enum DecisionCategory {BLOCK, WATCH, ACCEPT}

    public GetDecisionFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GetDecisionFieldSet setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetDecisionFieldSet setFrom(Integer from) {
        this.from = from;
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

    public Integer getFrom() {
        return from;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public List<AbuseType> getAbuseTypes() {
        return abuseTypes;
    }
}
