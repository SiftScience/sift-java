package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalProfileTransactions {
    @Expose @SerializedName("total") private Long total;
    @Expose @SerializedName("failed_fraud") private Long failedFraud;
    @Expose @SerializedName("failed_other") private Long failedOther;
    @Expose @SerializedName("successful") private Long successful;
    @Expose @SerializedName("last_timestamp") private Long lastTimestamp;
    @Expose @SerializedName("last_failed_fraud_timestamp") private Long lastFailedFraudTimestamp;

    public Long getTotal() {
        return total;
    }

    public GlobalProfileTransactions setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getFailedFraud() {
        return failedFraud;
    }

    public GlobalProfileTransactions setFailedFraud(Long failedFraud) {
        this.failedFraud = failedFraud;
        return this;
    }

    public Long getFailedOther() {
        return failedOther;
    }

    public GlobalProfileTransactions setFailedOther(Long failedOther) {
        this.failedOther = failedOther;
        return this;
    }

    public Long getSuccessful() {
        return successful;
    }

    public GlobalProfileTransactions setSuccessful(Long successful) {
        this.successful = successful;
        return this;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public GlobalProfileTransactions setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
        return this;
    }

    public Long getLastFailedFraudTimestamp() {
        return lastFailedFraudTimestamp;
    }

    public GlobalProfileTransactions setLastFailedFraudTimestamp(Long lastFailedFraudTimestamp) {
        this.lastFailedFraudTimestamp = lastFailedFraudTimestamp;
        return this;
    }
}
