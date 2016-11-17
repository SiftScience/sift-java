package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkflowStatusHistoryConfigButton {
    @Expose @SerializedName("id") private String id;
    @Expose @SerializedName("name") private String name;

    public String getId() {
        return id;
    }

    public WorkflowStatusHistoryConfigButton setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkflowStatusHistoryConfigButton setName(String name) {
        this.name = name;
        return this;
    }
}
