package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseFieldSet extends FieldSet<ResponseFieldSet> {

    @Expose @SerializedName("status") private Integer status;
    @Expose @SerializedName("request") private String request;
    @Expose @SerializedName("time") private Integer time;
    @Expose @SerializedName("error_message") private String errorMessage;
    @Expose @SerializedName("score_response") private ScoreResponse scoreResponse;

    @Override
    public String getEventType() {
        return null;
    }

    @Override
    protected boolean allowCustomFields() {
        return false;
    }

    public static ResponseFieldSet fromJson(String json) {
        return gson.fromJson(json, ResponseFieldSet.class);
    }

    public Integer getStatus() {
        return status;
    }

    public ResponseFieldSet setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public ResponseFieldSet setRequest(String request) {
        this.request = request;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public ResponseFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseFieldSet setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ScoreResponse getScoreResponse() {
        return scoreResponse;
    }

    public ResponseFieldSet setScoreResponse(ScoreResponse scoreResponse) {
        this.scoreResponse = scoreResponse;
        return this;
    }
}
