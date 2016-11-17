package com.siftscience.model;

public class CreateContentFieldSet extends BaseContentFieldSet<CreateContentFieldSet> {
    public static CreateContentFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateContentFieldSet.class);
    }

    @Override
    public String getEventType() {
        return "$create_content";
    }
}
