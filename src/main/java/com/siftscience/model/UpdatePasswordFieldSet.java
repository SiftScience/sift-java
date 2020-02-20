package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePasswordFieldSet extends BaseAppBrowserSiteBrandFieldSet<UpdatePasswordFieldSet> {
    public static UpdatePasswordFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdatePasswordFieldSet.class);
    }

    @Expose @SerializedName("$reason") private String reason;
    @Expose @SerializedName("$status") private String status;

    @Override
    public String getEventType() {
        return "$update_password";
    }

    public String getReason() { return reason; }

    public UpdatePasswordFieldSet setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getStatus() { return status; }

    public UpdatePasswordFieldSet setStatus(String status) {
        this.status = status;
        return this;
    }
}
