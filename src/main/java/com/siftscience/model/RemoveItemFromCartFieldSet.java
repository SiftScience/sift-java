package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveItemFromCartFieldSet extends FieldSet<RemoveItemFromCartFieldSet> {

    public static RemoveItemFromCartFieldSet fromJson(String json) {
        return gson.fromJson(json, RemoveItemFromCartFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$item") private Item item;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$remove_item_from_cart";
    }

    public String getUserId() {
        return userId;
    }

    public RemoveItemFromCartFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public RemoveItemFromCartFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public RemoveItemFromCartFieldSet setItem(Item item) {
        this.item = item;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public RemoveItemFromCartFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public RemoveItemFromCartFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
