package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DecisionLog extends BaseResponseBody<DecisionLog> {
    @Expose @SerializedName("entity") private EntityJson entity;
    @Expose @SerializedName("decision") private RefJson decision;
    @Expose @SerializedName("source") private DecisionSourceJson source;
    @Expose @SerializedName("time") private Long time;
    @Expose @SerializedName("webhook_succeeded") private Boolean webhook_succeeded;

    private class EntityJson {
        @Expose @SerializedName("id") private String id;
        @Expose @SerializedName("type") private String type;
        @Expose @SerializedName("ref") private RefJson ref;
    };

    private static class DecisionSourceJson {
        @Expose @SerializedName("type") private Type type;
        @Expose @SerializedName("ref") private RefJson ref;

        enum Type {WORKFLOW,ANALYST,AUTOMATED_RULE}
    };

    private class RefJson {
        @Expose @SerializedName("id") private String id;
        @Expose @SerializedName("version") private String version;
        @Expose @SerializedName("href") private String href;
        @Expose @SerializedName("title") private String title;
    }

    public static DecisionLog fromJson(String json) {
         return gson.fromJson(json, DecisionLog.class);
    }

    public EntityJson getEntity() {
        return entity;
    }

    public RefJson getDecision() {
        return decision;
    }

    public DecisionSourceJson getSource() {
        return source;
    }

    public Long getTime() {
        return time;
    }

    public Boolean getWebhook_succeeded() {
        return webhook_succeeded;
    }
}
