package com.siftscience.model;

public class LinkSessionToUserFieldSet extends
        EventsApiRequestFieldSet<LinkSessionToUserFieldSet> {
    public static LinkSessionToUserFieldSet fromJson(String json) {
        return gson.fromJson(json, LinkSessionToUserFieldSet.class);
    }

    @Override
    public String getEventType() {
        return "$link_session_to_user";
    }
}
