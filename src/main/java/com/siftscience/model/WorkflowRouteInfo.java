package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkflowRouteInfo {
    @Expose
    @SerializedName("name") private String name;

    public String getName() {
        return name;
    }

    public WorkflowRouteInfo setName(String name) {
        this.name = name;
        return this;
    }
}
