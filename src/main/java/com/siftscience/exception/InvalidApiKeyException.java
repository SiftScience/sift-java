package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class InvalidApiKeyException extends InvalidFieldException {
    public InvalidApiKeyException(SiftResponse response) {
        super(response);
    }
}
