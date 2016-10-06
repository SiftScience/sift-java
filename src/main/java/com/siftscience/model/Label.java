package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Label {

    @Expose @SerializedName("is_bad") private Boolean isBad;
    @Expose @SerializedName("time") private Long time;
    @Expose @SerializedName("description") private String description;

    public Boolean getBad() {
        return isBad;
    }

    public Label setBad(Boolean bad) {
        isBad = bad;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public Label setTime(Long time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Label setDescription(String description) {
        this.description = description;
        return this;
    }
}
