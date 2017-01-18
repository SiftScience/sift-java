package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DecisionLog extends BaseResponseBody<DecisionLog> {
    @Expose @SerializedName("entity") private EntityJson entity;
    @Expose @SerializedName("decision") private DecisionSourceJson decision;
    @Expose @SerializedName("time") private Long time;

    private class EntityJson {
        @Expose @SerializedName("id") private String id;
        @Expose @SerializedName("type") private String type;
    }

    private static class DecisionSourceJson {
        @Expose @SerializedName("id") private String id;
    }

    public static DecisionLog fromJson(String json) {
        return gson.fromJson(json, DecisionLog.class);
    }

    public EntityJson getEntity() {
        return entity;
    }

    public DecisionSourceJson getDecision() {
        return decision;
    }

    public Long getTime() {
        return time;
    }
}

