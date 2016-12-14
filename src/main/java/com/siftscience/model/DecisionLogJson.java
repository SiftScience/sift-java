package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DecisionLogJson extends BaseResponseBody<DecisionLogJson> {
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

    public static DecisionLogJson fromJson(String json) {
         return gson.fromJson(json, DecisionLogJson.class);
    }
}
