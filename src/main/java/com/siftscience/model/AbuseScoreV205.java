package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbuseScoreV205 {
    @Expose @SerializedName("score") private Double score;
    @Expose @SerializedName("reasons") private List<Reason> reasons;
    @Expose @SerializedName("status") private int status;
    @Expose @SerializedName("error_message") private String error_message;
    @Expose @SerializedName("time") private long time;

    public Double getScore() {
        return score;
    }

    public AbuseScoreV205 setScore(Double score) {
        this.score = score;
        return this;
    }

    public List<Reason> getReasons() {
        return reasons;
    }

    public AbuseScoreV205 setReasons(List<Reason> reasons) {
        this.reasons = reasons;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public AbuseScoreV205 setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getError_message() {
        return error_message;
    }

    public AbuseScoreV205 setError_message(String error_message) {
        this.error_message = error_message;
        return this;
    }

    public long getTime() {
        return time;
    }

    public AbuseScoreV205 setTime(long time) {
        this.time = time;
        return this;
    }

}
