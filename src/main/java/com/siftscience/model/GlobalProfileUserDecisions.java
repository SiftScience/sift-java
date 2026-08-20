package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalProfileUserDecisions {
    @Expose @SerializedName("total") private Long total;
    @Expose @SerializedName("blocked") private Long blocked;
    @Expose @SerializedName("watched") private Long watched;
    @Expose @SerializedName("accepted") private Long accepted;
    @Expose @SerializedName("manual") private Long manual;
    @Expose @SerializedName("auto") private Long auto;
    @Expose @SerializedName("last_type") private String lastType;
    @Expose @SerializedName("last_timestamp") private Long lastTimestamp;

    public Long getTotal() {
        return total;
    }

    public GlobalProfileUserDecisions setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getBlocked() {
        return blocked;
    }

    public GlobalProfileUserDecisions setBlocked(Long blocked) {
        this.blocked = blocked;
        return this;
    }

    public Long getWatched() {
        return watched;
    }

    public GlobalProfileUserDecisions setWatched(Long watched) {
        this.watched = watched;
        return this;
    }

    public Long getAccepted() {
        return accepted;
    }

    public GlobalProfileUserDecisions setAccepted(Long accepted) {
        this.accepted = accepted;
        return this;
    }

    public Long getManual() {
        return manual;
    }

    public GlobalProfileUserDecisions setManual(Long manual) {
        this.manual = manual;
        return this;
    }

    public Long getAuto() {
        return auto;
    }

    public GlobalProfileUserDecisions setAuto(Long auto) {
        this.auto = auto;
        return this;
    }

    public String getLastType() {
        return lastType;
    }

    public GlobalProfileUserDecisions setLastType(String lastType) {
        this.lastType = lastType;
        return this;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public GlobalProfileUserDecisions setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
        return this;
    }
}
