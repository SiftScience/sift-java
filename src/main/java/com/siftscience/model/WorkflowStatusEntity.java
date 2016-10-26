package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkflowStatusEntity {
    @Expose @SerializedName("type") private String type;
    @Expose @SerializedName("id") private String id;

    public String getType() {
        return type;
    }

    public WorkflowStatusEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public WorkflowStatusEntity setId(String id) {
        this.id = id;
        return this;
    }
}
