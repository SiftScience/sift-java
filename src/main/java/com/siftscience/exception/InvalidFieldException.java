package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class InvalidFieldException extends InvalidRequestException {
    public InvalidFieldException(String msg) {
        super(msg);
    }

    public InvalidFieldException(SiftResponse response) {
        super(response);
    }
}
