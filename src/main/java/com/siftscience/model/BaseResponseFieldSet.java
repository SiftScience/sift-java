package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public abstract class BaseResponseFieldSet<T extends BaseResponseFieldSet<T>> extends FieldSet<T> {

    @Expose @SerializedName("status") private Integer status;
    @Expose @SerializedName("error_message") private String errorMessage;

    @Override
    public String getEventType() {
        return null;
    }

    @Override
    protected boolean allowCustomFields() {
        return false;
    }

    public Integer getStatus() {
        return status;
    }

    public T setStatus(Integer status) {
        this.status = status;
        return (T) this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return (T) this;
    }
}
