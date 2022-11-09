package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class RiskProfile {

    @Expose @SerializedName("level") private String level = null;

    @Expose @SerializedName("score") private Long score = null;

    public String getLevel() {
        return level;
    }

    public RiskProfile setLevel(MerchantRiskLevelEnum riskLevel) {
        this.level = riskLevel.value;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public RiskProfile setScore(Long score) {
        this.score = score;
        return this;
    }
}
