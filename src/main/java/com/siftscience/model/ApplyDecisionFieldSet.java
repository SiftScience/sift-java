package com.siftscience.model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;
import com.siftscience.exception.MissingFieldException;

import java.lang.reflect.Type;

public class ApplyDecisionFieldSet extends FieldSet<ApplyDecisionFieldSet> {

    public enum DecisionSource {
        MANUAL_REVIEW("manual_review"),
        AUTOMATED_RULE("automated_rule"),
        CHARGEBACK("chargeback");

        private final String value;

        DecisionSource(String value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return value;
        }

        static class DecisionSourceDeserializer implements JsonDeserializer<DecisionSource> {
            @Override
            public DecisionSource deserialize(JsonElement json,
                                              Type typeOfT,
                                              JsonDeserializationContext context)
                    throws JsonParseException {
                return DecisionSource.valueOf(json.getAsString().toUpperCase());
            }
        }

        static class DecisionSourceSerializer implements JsonSerializer<DecisionSource> {
            @Override
            public JsonElement serialize(DecisionSource source, Type typeOfSrc, JsonSerializationContext context) {
                return gson.toJsonTree(source.value);
            }
        }
    }


    @Expose @SerializedName("decision_id") private String decisionId;
    @Expose @SerializedName("source") private DecisionSource source;
    @Expose @SerializedName("analyst") private String analyst;
    @Expose @SerializedName("time") private long time;
    private String accountId;
    private String userId;
    private String orderId;

    public ApplyDecisionFieldSet() {}

    public ApplyDecisionFieldSet setDecisionId(String decisionId) {
        this.decisionId = decisionId;
        return this;
    }

    public ApplyDecisionFieldSet setSource(DecisionSource source) {
        this.source = source;
        return this;
    }

    public ApplyDecisionFieldSet setAnalyst(String analyst) {
        this.analyst = analyst;
        return this;
    }

    public ApplyDecisionFieldSet setTime(long time) {
        this.time = time;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public ApplyDecisionFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ApplyDecisionFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public ApplyDecisionFieldSet setOrderId(String orderId) {
        this.orderId = orderId;
            return this;
    }

    @Override
    public void validate() {
        super.validate();
        if (DecisionSource.MANUAL_REVIEW.equals(source) && (analyst == null || analyst.isEmpty())) {
            throw new MissingFieldException("'analyst' required for decisions " +
                    "with source type MANUAL_REVIEW");
        }
    }
}
