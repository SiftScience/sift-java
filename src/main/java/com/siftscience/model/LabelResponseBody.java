package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelResponseBody extends BaseResponseBody<LabelResponseBody> {

    public static LabelResponseBody fromJson(String json) {
        return gson.fromJson(json, LabelResponseBody.class);
    }

    @Expose @SerializedName("request") private String request;
    @Expose @SerializedName("time") private Integer time;
    @Expose @SerializedName("score_response") private ScoreResponse scoreResponse;

    public String getRequest() {
        return request;
    }

    public LabelResponseBody setRequest(String request) {
        this.request = request;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public LabelResponseBody setTime(Integer time) {
        this.time = time;
        return this;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public LabelResponseBody setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
        return this;
    }
}
