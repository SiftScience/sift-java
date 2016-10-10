package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResponseFieldSet extends BaseResponseFieldSet<EventResponseFieldSet> {

    public static EventResponseFieldSet fromJson(String json) {
        return gson.fromJson(json, EventResponseFieldSet.class);
    }

    @Expose @SerializedName("request") private String request;
    @Expose @SerializedName("time") private Integer time;
    @Expose @SerializedName("score_response") private ScoreResponse scoreResponse;

    public String getRequest() {
        return request;
    }

    public EventResponseFieldSet setRequest(String request) {
        this.request = request;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public EventResponseFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public EventResponseFieldSet setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
        return this;
    }
}
