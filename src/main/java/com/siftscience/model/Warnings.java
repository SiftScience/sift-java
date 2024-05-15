package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warnings {
    @Expose
    @SerializedName("count")
    private Integer count;
    @Expose
    @SerializedName("items")
    private List<WarningItem> warningItems;

    public Integer getCount() {
        return count;
    }

    public Warnings setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<WarningItem> getWarningItems() {
        return warningItems;
    }

    public Warnings setWarningItems(List<WarningItem> warningItems) {
        this.warningItems = warningItems;
        return this;
    }
}
