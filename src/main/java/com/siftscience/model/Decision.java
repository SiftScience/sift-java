package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Decision {
    @Expose @SerializedName("id") private String id;

    public String getId() {
        return id;
    }

    public Decision setId(String id) {
        this.id = id;
        return this;
    }
}
