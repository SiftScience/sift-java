package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ScoreResponseBody extends BaseResponseBody<ScoreResponseBody> {
    public static ScoreResponseBody fromJson(String json) {
        return gson.fromJson(json, ScoreResponseBody.class);
    }

    @Expose @SerializedName("user_id") private String userId;
    @Expose @SerializedName("scores") private Map<String, AbuseScore> scores;
    @Expose @SerializedName("latest_labels") private Map<String, Label> latestLabels;

    public String getUserId() {
        return userId;
    }

    public ScoreResponseBody setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Map<String, AbuseScore> getScores() {
        return scores;
    }

    public ScoreResponseBody setScores(Map<String, AbuseScore> scores) {
        this.scores = scores;
        return this;
    }

    public Map<String, Label> getLatestLabels() {
        return latestLabels;
    }

    public ScoreResponseBody setLatestLabels(Map<String, Label> latestLabels) {
        this.latestLabels = latestLabels;
        return this;
    }
}
