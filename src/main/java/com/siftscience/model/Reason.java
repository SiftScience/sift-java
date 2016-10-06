package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Reason {
    @Expose @SerializedName("name") private String name;
    @Expose @SerializedName("value") private Object value;
    @Expose @SerializedName("details") private Map<String, Object> details;

    public String getName() {
        return name;
    }

    public Reason setName(String name) {
        this.name = name;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Reason setValue(Object value) {
        this.value = value;
        return this;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public Reason setDetails(Map<String, Object> details) {
        this.details = details;
        return this;
    }
}
