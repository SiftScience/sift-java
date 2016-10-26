package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkflowStatus extends BaseResponseBody<WorkflowStatus> {
    @Expose @SerializedName("id") private String id;
    @Expose @SerializedName("state") private String state;
    @Expose @SerializedName("config") private WorkflowStatusConfig config;
    @Expose @SerializedName("config_display_name") private String configDisplayName;
    @Expose @SerializedName("abuse_types") private List<String> abuseTypes;
    @Expose @SerializedName("entity") private WorkflowStatusEntity entity;
    @Expose @SerializedName("history") private List<WorkflowStatusHistoryItem> history;

    public static WorkflowStatus fromJson(String jsonBody) {
        return gson.fromJson(jsonBody, WorkflowStatus.class);
    }

    public String getId() {
        return id;
    }

    public WorkflowStatus setId(String id) {
        this.id = id;
        return this;
    }

    public String getState() {
        return state;
    }

    public WorkflowStatus setState(String state) {
        this.state = state;
        return this;
    }

    public WorkflowStatusConfig getConfig() {
        return config;
    }

    public WorkflowStatus setConfig(WorkflowStatusConfig config) {
        this.config = config;
        return this;
    }

    public String getConfigDisplayName() {
        return configDisplayName;
    }

    public WorkflowStatus setConfigDisplayName(String configDisplayName) {
        this.configDisplayName = configDisplayName;
        return this;
    }

    public List<String> getAbuseTypes() {
        return abuseTypes;
    }

    public WorkflowStatus setAbuseTypes(List<String> abuseTypes) {
        this.abuseTypes = abuseTypes;
        return this;
    }

    public WorkflowStatusEntity getEntity() {
        return entity;
    }

    public WorkflowStatus setEntity(WorkflowStatusEntity entity) {
        this.entity = entity;
        return this;
    }

    public List<WorkflowStatusHistoryItem> getHistory() {
        return history;
    }

    public WorkflowStatus setHistory(List<WorkflowStatusHistoryItem> history) {
        this.history = history;
        return this;
    }
}
