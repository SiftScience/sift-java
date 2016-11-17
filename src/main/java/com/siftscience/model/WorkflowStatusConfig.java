package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkflowStatusConfig {
    @Expose @SerializedName("id") private String id;
    @Expose @SerializedName("version") private String version;

    public String getId() {
        return id;
    }

    public WorkflowStatusConfig setId(String id) {
        this.id = id;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public WorkflowStatusConfig setVersion(String version) {
        this.version = version;
        return this;
    }
}
