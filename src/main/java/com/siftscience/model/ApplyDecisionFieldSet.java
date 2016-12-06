package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;
import com.siftscience.exception.MissingFieldException;

public class ApplyDecisionFieldSet extends FieldSet<ApplyDecisionFieldSet> {

    public enum DecisionSource {
        MANUAL_REVIEW, AUTOMATED_RULE, CHARGEBACK
    }

    @Expose @SerializedName("decision_id") private String decisionId;
    @Expose @SerializedName("source") private DecisionSource source;
    @Expose @SerializedName("analyst") private String analyst;
    @Expose @SerializedName("time") private long time;

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

    @Override
    public void validate() {
        super.validate();
        if (DecisionSource.MANUAL_REVIEW.equals(source) && (analyst == null || analyst.isEmpty())) {
            throw new MissingFieldException("'analyst' required for decisions " +
                    "with source type MANUAL_REVIEW");
        }
    }
}
