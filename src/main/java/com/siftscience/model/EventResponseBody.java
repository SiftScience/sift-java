package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.EventResponse;
import com.sun.istack.internal.Interned;

public class EventResponseBody extends BaseResponseBody<EventResponseBody> {
    public static EventResponseBody fromJson(String json) {
        return gson.fromJson(json, EventResponseBody.class);
    }

    @Expose @SerializedName("request") private String request;
    @Expose @SerializedName("time") private Integer time;
    @Expose @SerializedName("score_response") private ScoreResponse scoreResponse;

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

    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    @Override
    public EventResponseBody setStatus(Integer status) {
        return super.setStatus(status);
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public EventResponseBody setErrorMessage(String errorMessage) {
        return super.setErrorMessage(errorMessage);
    }
}
