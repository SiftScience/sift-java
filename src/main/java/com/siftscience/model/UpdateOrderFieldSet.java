package com.siftscience.model;

public class UpdateOrderFieldSet extends BaseOrderFieldSet<UpdateOrderFieldSet> {
    public String getEventType() {
        return "$update_order";
    }

    public static UpdateOrderFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateOrderFieldSet.class);
    }
}
