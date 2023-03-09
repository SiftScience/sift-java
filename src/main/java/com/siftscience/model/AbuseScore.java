package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class AbuseScore {
    @Expose @SerializedName("score") private Double score;
    @Expose @SerializedName("reasons") private List<Reason> reasons;
    @Expose @SerializedName("percentiles") private Map<String, Double> percentiles;
    @Expose @SerializedName("status") private int status;
    @Expose @SerializedName("error_message") private String errorMessage;
    @Expose @SerializedName("time") private long time;

    public Double getScore() {
        return score;
    }

    public AbuseScore setScore(Double score) {
        this.score = score;
        return this;
    }

    public List<Reason> getReasons() {
        return reasons;
    }

    public AbuseScore setReasons(List<Reason> reasons) {
        this.reasons = reasons;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public AbuseScore setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AbuseScore setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public long getTime() {
        return time;
    }

    public AbuseScore setTime(long time) {
        this.time = time;
        return this;
    }

    public Map<String, Double> getPercentiles() {
        return percentiles;
    }

    public void setPercentiles(Map<String, Double> percentiles) {
        this.percentiles = percentiles;
    }
}
