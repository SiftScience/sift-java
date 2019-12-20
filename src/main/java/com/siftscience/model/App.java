package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {
    @Expose @SerializedName("$os") private String operatingSystem;
    @Expose @SerializedName("$os_version") private String operatingSystemVersion;
    @Expose @SerializedName("$device_manufacturer") private String deviceManufacturer;
    @Expose @SerializedName("$device_model") private String deviceModel;
    @Expose @SerializedName("$device_unique_id") private String deviceUniqueId;
    @Expose @SerializedName("$app_name") private String appName;
    @Expose @SerializedName("$app_version") private String appVersion;
    @Expose @SerializedName("$client_language") private String clientLanguage;

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public App setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public App setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
        return this;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public App setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
        return this;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public App setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    public String getDeviceUniqueId() {
        return deviceUniqueId;
    }

    public App setDeviceUniqueId(String deviceUniqueId) {
        this.deviceUniqueId = deviceUniqueId;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public App setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public App setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getClientLanguage() {
        return clientLanguage;
    }

    public App setClientLanguage(String clientLanguage) {
        this.clientLanguage = clientLanguage;
        return this;
    }
}
