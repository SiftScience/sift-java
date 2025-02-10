package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WagerFieldSet  extends EventsApiRequestFieldSet<WagerFieldSet> {

    @Expose @SerializedName("$wager_id") private String wagerId;
    @Expose @SerializedName("$wager_type") private String wagerType;
    @Expose @SerializedName("$wager_status") private String wagerStatus;
    @Expose @SerializedName("$amount") private Long amount;
    @Expose @SerializedName("$amount_usd") private Long amountUsd;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$wager_event_type") private String wagerEventType;
    @Expose @SerializedName("$wager_event_name") private String wagerEventName;
    @Expose @SerializedName("$wager_event_id") private String wagerEventId;
    @Expose @SerializedName("$minimum_wager_amount") private Long minimumWagerAmount;

    @Override
    public String getEventType() {
        return "$wager";
    }

    public static WagerFieldSet fromJson(String json) {
        return gson.fromJson(json, WagerFieldSet.class);
    }

    public String getWagerId() {
        return wagerId;
    }

    public WagerFieldSet setWagerId(String wagerId) {
        this.wagerId = wagerId;
        return this;
    }

    public String getWagerType() {
        return wagerType;
    }

    public WagerFieldSet setWagerType(String wagerType) {
        this.wagerType = wagerType;
        return this;
    }

    public String getWagerStatus() {
        return wagerStatus;
    }

    public WagerFieldSet setWagerStatus(String wagerStatus) {
        this.wagerStatus = wagerStatus;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public WagerFieldSet setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public WagerFieldSet setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getWagerEventType() {
        return wagerEventType;
    }

    public WagerFieldSet setWagerEventType(String wagerEventType) {
        this.wagerEventType = wagerEventType;
        return this;
    }

    public String getWagerEventName() {
        return wagerEventName;
    }

    public WagerFieldSet setWagerEventName(String wagerEventName) {
        this.wagerEventName = wagerEventName;
        return this;
    }

    public String getWagerEventId() {
        return wagerEventId;
    }

    public WagerFieldSet setWagerEventId(String wagerEventId) {
        this.wagerEventId = wagerEventId;
        return this;
    }

    public Long getMinimumWagerAmount() {
        return minimumWagerAmount;
    }

    public WagerFieldSet setMinimumWagerAmount(Long minimumWagerAmount) {
        this.minimumWagerAmount = minimumWagerAmount;
        return this;
    }

    public Long getAmountUsd() {
        return amountUsd;
    }

    public WagerFieldSet setAmountUsd(Long amountUsd) {
        this.amountUsd = amountUsd;
        return this;
    }
}
