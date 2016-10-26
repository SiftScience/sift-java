package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestDecision {
    @Expose @SerializedName("decision") private Decision decision;
    @Expose @SerializedName("time") private Long time;
    @Expose @SerializedName("webhook_succeeded") private Boolean webhookSucceeded;

    public Decision getDecision() {
        return decision;
    }

    public LatestDecision setDecision(Decision decision) {
        this.decision = decision;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public LatestDecision setTime(Long time) {
        this.time = time;
        return this;
    }

    public Boolean getWebhookSucceeded() {
        return webhookSucceeded;
    }

    public LatestDecision setWebhookSucceeded(Boolean webhookSucceeded) {
        this.webhookSucceeded = webhookSucceeded;
        return this;
    }
}
