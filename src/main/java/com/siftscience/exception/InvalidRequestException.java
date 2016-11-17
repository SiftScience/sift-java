package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class InvalidRequestException extends SiftException {
    public InvalidRequestException(String msg) {
        super(msg);
    }

    public InvalidRequestException(SiftResponse response) {
        super(response);
    }
}
