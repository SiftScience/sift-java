package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.ApplyDecisionRequest;
import com.siftscience.ApplyDecisionResponse;

public class ApplyDecisionResponseBody extends BaseResponseBody<ApplyDecisionResponseBody> {
    @Expose @SerializedName("time") private Long time;
    @Expose @SerializedName("request") private ApplyDecisionFieldSet request;

    public static ApplyDecisionResponseBody fromJson(String json) {
        return gson.fromJson(json, ApplyDecisionResponseBody.class);
    }
    public ApplyDecisionResponseBody(long time, ApplyDecisionFieldSet request) {
        this.time = time;
        this.request = request;
    }

    public Long getTime() {
        return time;
    }

    public ApplyDecisionResponseBody setTime(Long time) {
        this.time = time;
        return this;
    }

    public ApplyDecisionFieldSet getRequest() {
        return request;
    }

    public ApplyDecisionResponseBody setRequest(ApplyDecisionFieldSet request) {
        this.request = request;
        return this;
    }
}
