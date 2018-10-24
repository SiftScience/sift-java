package com.siftscience.model;

public class LogoutFieldSet extends BaseAppBrowserFieldSet<LogoutFieldSet> {
    public static LogoutFieldSet fromJson(String json) {
        return gson.fromJson(json, LogoutFieldSet.class);
    }

    @Override
    public String getEventType() {
        return "$logout";
    }
}
