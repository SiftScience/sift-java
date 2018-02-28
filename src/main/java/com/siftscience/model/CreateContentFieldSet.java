package com.siftscience.model;

public abstract class CreateContentFieldSet<T extends CreateContentFieldSet<T>>
    extends BaseContentFieldSet<T> {
    @Override
    public String getEventType() {
        return "$create_content";
    }
}
