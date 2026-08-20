package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalProfileOrders {
    @Expose @SerializedName("total") private Long total;
    @Expose @SerializedName("blocked") private Long blocked;
    @Expose @SerializedName("watched") private Long watched;
    @Expose @SerializedName("accepted") private Long accepted;
    @Expose @SerializedName("last_timestamp") private Long lastTimestamp;
    @Expose @SerializedName("last_blocked_timestamp") private Long lastBlockedTimestamp;

    public Long getTotal() {
        return total;
    }

    public GlobalProfileOrders setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getBlocked() {
        return blocked;
    }

    public GlobalProfileOrders setBlocked(Long blocked) {
        this.blocked = blocked;
        return this;
    }

    public Long getWatched() {
        return watched;
    }

    public GlobalProfileOrders setWatched(Long watched) {
        this.watched = watched;
        return this;
    }

    public Long getAccepted() {
        return accepted;
    }

    public GlobalProfileOrders setAccepted(Long accepted) {
        this.accepted = accepted;
        return this;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public GlobalProfileOrders setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
        return this;
    }

    public Long getLastBlockedTimestamp() {
        return lastBlockedTimestamp;
    }

    public GlobalProfileOrders setLastBlockedTimestamp(Long lastBlockedTimestamp) {
        this.lastBlockedTimestamp = lastBlockedTimestamp;
        return this;
    }
}
