package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class SiftException extends RuntimeException {
    private SiftResponse response;

    public SiftException(String msg) {
        super(msg);
    }

    public SiftException(SiftResponse response) {
        super(responseErrorMessage(response));
        this.response = response;
    }

    public SiftResponse getSiftResponse() {
        return this.response;
    }

    private static String responseErrorMessage(SiftResponse response) {
        if (response == null) {
            return "Unexpected API error.";
        } else if (response.getApiErrorMessage() == null) {
            return "Unexpected API error " + response.getHttpStatusCode() + ".";
        }
        return response.getApiErrorMessage();
    }

    public String getApiErrorMessage() {
        return responseErrorMessage(response);
    }
}
