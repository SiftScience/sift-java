package com.siftscience.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarningsResponse {
    @Expose
    @SerializedName("count")
    private Integer count;
    @Expose
    @SerializedName("items")
    private List<WarningItem> warningItems;

    public Integer getCount() {
        return count;
    }

    public WarningsResponse setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<WarningItem> getWarningItems() {
        return warningItems;
    }

    public WarningsResponse setWarningItems(List<WarningItem> warningItems) {
        this.warningItems = warningItems;
        return this;
    }
}
