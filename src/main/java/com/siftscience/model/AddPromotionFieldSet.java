package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

import java.util.List;

public class AddPromotionFieldSet extends FieldSet<AddPromotionFieldSet> {

    public static AddPromotionFieldSet fromJson(String json) {
        return gson.fromJson(json, AddPromotionFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$promotions") private List<Promotion> promotions;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$add_promotion";
    }

    public String getUserId() {
        return userId;
    }

    public AddPromotionFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public AddPromotionFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public AddPromotionFieldSet setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public AddPromotionFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AddPromotionFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
