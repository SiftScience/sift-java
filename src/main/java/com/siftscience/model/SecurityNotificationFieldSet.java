package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecurityNotificationFieldSet extends EventsApiRequestFieldSet<SecurityNotificationFieldSet> {
    public static SecurityNotificationFieldSet fromJson(String json) {
        return gson.fromJson(json, SecurityNotificationFieldSet.class);
    }

    @Expose @SerializedName("$notification_status") private String notificationStatus;
    @Expose @SerializedName("$notification_type") private String notificationType;
    @Expose @SerializedName("$notified_value") private String notifiedValue;

    @Override
    public String getEventType() {
        return "$security_notification";
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public SecurityNotificationFieldSet setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
        return this;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public SecurityNotificationFieldSet setNotificationType(String notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public String getNotifiedValue() {
        return notifiedValue;
    }

    public SecurityNotificationFieldSet setNotifiedValue(String notifiedValue) {
        this.notifiedValue = notifiedValue;
        return this;
    }
}
