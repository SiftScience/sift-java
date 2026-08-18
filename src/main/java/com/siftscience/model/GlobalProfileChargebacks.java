package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalProfileChargebacks {
    @Expose @SerializedName("total") private Long total;
    @Expose @SerializedName("fraudulent") private Long fraudulent;
    @Expose @SerializedName("other") private Long other;
    @Expose @SerializedName("last_timestamp") private Long lastTimestamp;
    @Expose @SerializedName("last_fraudulent_timestamp") private Long lastFraudulentTimestamp;

    public Long getTotal() {
        return total;
    }

    public GlobalProfileChargebacks setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getFraudulent() {
        return fraudulent;
    }

    public GlobalProfileChargebacks setFraudulent(Long fraudulent) {
        this.fraudulent = fraudulent;
        return this;
    }

    public Long getOther() {
        return other;
    }

    public GlobalProfileChargebacks setOther(Long other) {
        this.other = other;
        return this;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public GlobalProfileChargebacks setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
        return this;
    }

    public Long getLastFraudulentTimestamp() {
        return lastFraudulentTimestamp;
    }

    public GlobalProfileChargebacks setLastFraudulentTimestamp(Long lastFraudulentTimestamp) {
        this.lastFraudulentTimestamp = lastFraudulentTimestamp;
        return this;
    }
}
