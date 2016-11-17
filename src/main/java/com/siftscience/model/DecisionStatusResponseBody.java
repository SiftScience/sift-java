package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DecisionStatusResponseBody extends BaseResponseBody<DecisionStatusResponseBody> {
    @Expose @SerializedName("decisions") private Map<String, LatestDecision> decisions;

    public static DecisionStatusResponseBody fromJson(String jsonBody) {
        return gson.fromJson(jsonBody, DecisionStatusResponseBody.class);
    }

    public Map<String, LatestDecision> getDecisions() {
        return decisions;
    }

    public DecisionStatusResponseBody setDecisions(Map<String, LatestDecision> decisions) {
        this.decisions = decisions;
        return this;
    }
}
