package com.siftscience.model;

public class CreateOrderFieldSet extends BaseOrderFieldSet<CreateOrderFieldSet> {
    @Override
    public String getEventType() {
        return "$create_order";
    }

    public static CreateOrderFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateOrderFieldSet.class);
    }
}
