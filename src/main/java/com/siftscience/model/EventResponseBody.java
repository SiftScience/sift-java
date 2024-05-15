package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResponseBody extends BaseResponseBody<EventResponseBody> {
    public static EventResponseBody fromJson(String json) {
        return gson.fromJson(json, EventResponseBody.class);
    }

    @Expose
    @SerializedName("request")
    private String request;
    @Expose
    @SerializedName("time")
    private Integer time;
    @Expose
    @SerializedName("score_response")
    private ScoreResponse scoreResponse;
    @Expose
    @SerializedName("warnings")
    private Warnings warnings;

    public String getRequest() {
        return request;
    }

    public EventResponseBody setRequest(String request) {
        this.request = request;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public EventResponseBody setTime(Integer time) {
        this.time = time;
        return this;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public EventResponseBody setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
        return this;
    }

    public Warnings getWarnings() {
        return warnings;
    }

    public EventResponseBody setWarnings(Warnings warnings) {
        this.warnings = warnings;
        return this;
    }
}
