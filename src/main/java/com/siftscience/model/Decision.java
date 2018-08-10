package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Decision {
    @Expose @SerializedName("id") private String id;
    @Expose @SerializedName("category") private String category;
    @Expose @SerializedName("time") private long time;
    @Expose @SerializedName("source") private String source;
    @Expose @SerializedName("description") private String description;

    public String getId() {
        return id;
    }

    public Decision setId(String id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Decision setCategory(String category) {
        this.category = category;
        return this;
    }

    public long getTime() {
        return time;
    }

    public Decision setTime(long time) {
        this.time = time;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Decision setSource(String source) {
        this.source = source;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Decision setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Decision)) {
            return false;
        }

        Decision o = (Decision) other;
        return Objects.equals(getId(), o.getId())
                && Objects.equals(getCategory(), o.getCategory())
                && getTime() == o.getTime()
                && Objects.equals(getSource(), o.getSource())
                && Objects.equals(getDescription(), o.getDescription());
    }
}
