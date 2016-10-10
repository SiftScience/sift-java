package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelResponseFieldSet extends BaseResponseFieldSet<LabelResponseFieldSet> {

    public static LabelResponseFieldSet fromJson(String json) {
        return gson.fromJson(json, LabelResponseFieldSet.class);
    }

    @Expose @SerializedName("request") private String request;
    @Expose @SerializedName("time") private Integer time;
    @Expose @SerializedName("score_response") private ScoreResponse scoreResponse;

    public String getRequest() {
        return request;
    }

    public LabelResponseFieldSet setRequest(String request) {
        this.request = request;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public LabelResponseFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public LabelResponseFieldSet setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
        return this;
    }
}
