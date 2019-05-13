package com.siftscience.model;

import com.siftscience.FieldSet;
import com.siftscience.GetDecisionsRequest;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.exception.InvalidRequestException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDecisionFieldSet extends FieldSet<GetDecisionFieldSet> {
    private Integer limit;
    private Integer from;
    private EntityType entityType;
    private List<AbuseType> abuseTypes;

    public GetDecisionFieldSet() {}

    public static GetDecisionFieldSet fromJson(String json) {
        return gson.fromJson(json, GetDecisionFieldSet.class);
    }

    public static GetDecisionFieldSet fromNextRef(String nextRef) {
        Objects.requireNonNull(nextRef,"Must provide valid nextRef");
        URI uri = URI.create(nextRef);
        String queries = uri.getQuery();
            if (queries == null || queries.isEmpty()) {
            throw new InvalidRequestException("Invalid format for nextRef " + nextRef);
        } else {
            GetDecisionFieldSet fieldSet = new GetDecisionFieldSet();
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
        List<AbuseType> abuseTypes = new ArrayList<>();
        for (String abuseType : abuseTypeCsv.split(",")) {
            abuseTypes.add(AbuseType.valueOf(abuseType.toUpperCase()));
        }
        this.abuseTypes = Collections.unmodifiableList(abuseTypes);
    }

    public enum AbuseType {
        PAYMENT_ABUSE, CONTENT_ABUSE, PROMOTION_ABUSE, ACCOUNT_ABUSE, LEGACY, ACCOUNT_TAKEOVER
    }
    public enum EntityType {USER, ORDER, SESSION, CONTENT}
    public enum DecisionCategory {BLOCK, WATCH, ACCEPT}

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
