package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderStatusFieldSet extends FieldSet<OrderStatusFieldSet> {

    public static OrderStatusFieldSet fromJson(String json) {
        return gson.fromJson(json, OrderStatusFieldSet.class);
    }

    @Expose @SerializedName(USER_ID) private String userId;
    @Expose @SerializedName(SESSION_ID) private String sessionId;
    @Expose @SerializedName(TIME) private Integer time;
    @Expose @SerializedName(IP) private String ip;
    @Expose @SerializedName("$order_id") private String orderId;
    @Expose @SerializedName("$order_status") private String orderStatus;
    @Expose @SerializedName("$reason") private String reason;
    @Expose @SerializedName("$source") private String source;
    @Expose @SerializedName("$analyst") private String analyst;
    @Expose @SerializedName("$webhook_id") private String webhookId;
    @Expose @SerializedName("$description") private String description;

    @Override
    protected boolean allowCustomFields() {
        return true;
    }

    @Override
    public String getEventType() {
        return "$order_status";
    }


    @Override
    public void validate() {
        super.validate();
        validateStringField("$order_id", getOrderId());
        validateStringField("$order_status", getOrderStatus());
    }

    public String getUserId() {
        return userId;
    }

    public OrderStatusFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public OrderStatusFieldSet setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatusFieldSet setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderStatusFieldSet setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public OrderStatusFieldSet setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getSource() {
        return source;
    }

    public OrderStatusFieldSet setSource(String source) {
        this.source = source;
        return this;
    }

    public String getAnalyst() {
        return analyst;
    }

    public OrderStatusFieldSet setAnalyst(String analyst) {
        this.analyst = analyst;
        return this;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public OrderStatusFieldSet setWebhookId(String webhookId) {
        this.webhookId = webhookId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderStatusFieldSet setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public OrderStatusFieldSet setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public OrderStatusFieldSet setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
