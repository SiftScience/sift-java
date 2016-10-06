package com.siftscience.model;

public class UpdateContentFieldSet extends BaseContentFieldSet<UpdateContentFieldSet> {

    public static UpdateContentFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateContentFieldSet.class);
    }

    @Override
    public String getEventType() {
        return "$update_content";
    }
}
