package com.siftscience.exception;

import com.siftscience.SiftResponse;
import com.sun.istack.internal.NotNull;

public class SiftException extends RuntimeException {

    private SiftResponse response;

    public SiftException(String msg) {
        super(msg);
    }

    public SiftException(@NotNull SiftResponse response) {
        super(responseErrorMessage(response));
        this.response = response;
    }

    public SiftResponse getSiftResponse() {
        return this.response;
    }

    private static String responseErrorMessage(SiftResponse response) {
        if (response == null || response.getErrorMessage() == null) {
            return "Unexpected API error.";
        }
        return response.getErrorMessage();
    }
}
