package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.ApplyDecisionRequest;

public class ApplyDecisionResponseBody extends BaseResponseBody<ApplyDecisionResponseBody> {
    @Expose @SerializedName("time") private long time;
    @Expose @SerializedName("request") private ApplyDecisionFieldSet request;

    public static ApplyDecisionResponseBody fromJson(String json) {
        return gson.fromJson(json, ApplyDecisionResponseBody.class);
    }
    public ApplyDecisionResponseBody(long time, ApplyDecisionFieldSet request) {
        this.time = time;
        this.request = request;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ApplyDecisionFieldSet getRequest() {
        return request;
    }

    public void setRequest(ApplyDecisionFieldSet request) {
        this.request = request;
    }
}
