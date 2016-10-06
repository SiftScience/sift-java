package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class MissingFieldException extends InvalidRequestException {
    public MissingFieldException(String msg) {
        super(msg);
    }

    public MissingFieldException(SiftResponse response) {
        super(response);
    }

    public static String buildErrorMessageForKey(String key) {
        return "Required field \"" + key + "\" is missing.";
    }
}
