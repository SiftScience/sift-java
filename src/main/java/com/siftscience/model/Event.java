package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @Expose @SerializedName("$session_id") private String sessionId;
    @Expose @SerializedName("$verified_event") private String verifiedEvent;
    @Expose @SerializedName("$reason") private String reason;
    @Expose @SerializedName("$ip") private String ip;

    @Expose @SerializedName("$browser") private Browser browser;

    public String getSessionId() {
        return sessionId;
    }

    public Event setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getVerifiedEvent() {
        return verifiedEvent;
    }

    public Event setVerifiedEvent(String verifiedEvent) {
        this.verifiedEvent = verifiedEvent;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Event setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public Event setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Browser getBrowser() {
        return browser;
    }

    public Event setBrowser(Browser browser) {
        this.browser = browser;
        return this;
    }
}
