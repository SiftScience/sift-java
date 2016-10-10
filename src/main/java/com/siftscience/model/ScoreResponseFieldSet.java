package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ScoreResponseFieldSet extends BaseResponseFieldSet<ScoreResponseFieldSet> {

    public static ScoreResponseFieldSet fromJson(String json) {
        return gson.fromJson(json, ScoreResponseFieldSet.class);
    }

    @Expose @SerializedName("user_id") private String userId;
    @Expose @SerializedName("scores") private Map<String, AbuseScore> scores;
    @Expose @SerializedName("latest_labels") private Map<String, Label> latestLabels;

    public String getUserId() {
        return userId;
    }

    public ScoreResponseFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Map<String, AbuseScore> getScores() {
        return scores;
    }

    public ScoreResponseFieldSet setScores(Map<String, AbuseScore> scores) {
        this.scores = scores;
        return this;
    }

    public Map<String, Label> getLatestLabels() {
        return latestLabels;
    }

    public ScoreResponseFieldSet setLatestLabels(Map<String, Label> latestLabels) {
        this.latestLabels = latestLabels;
        return this;
    }
}
