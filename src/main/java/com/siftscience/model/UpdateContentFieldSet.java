package com.siftscience.model;

public abstract class UpdateContentFieldSet<T extends UpdateContentFieldSet<T>>
    extends BaseContentFieldSet<T> {
    @Override
    public String getEventType() {
        return "$update_content";
    }
}
