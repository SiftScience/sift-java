package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbuseScore {
    @Expose @SerializedName("score") private Double score;
    @Expose @SerializedName("reasons") private List<Reason> reasons;

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
}
