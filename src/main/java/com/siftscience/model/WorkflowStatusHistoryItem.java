package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkflowStatusHistoryItem {
    @Expose @SerializedName("app") private String app;
    @Expose @SerializedName("name") private String name;
    @Expose @SerializedName("state") private String state;
    @Expose @SerializedName("config") private WorkflowStatusHistoryConfig config;

    public String getApp() {
        return app;
    }

    public WorkflowStatusHistoryItem setApp(String app) {
        this.app = app;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkflowStatusHistoryItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public WorkflowStatusHistoryItem setState(String state) {
        this.state = state;
        return this;
    }

    public WorkflowStatusHistoryConfig getConfig() {
        return config;
    }

    public WorkflowStatusHistoryItem setConfig(WorkflowStatusHistoryConfig config) {
        this.config = config;
        return this;
    }
}
