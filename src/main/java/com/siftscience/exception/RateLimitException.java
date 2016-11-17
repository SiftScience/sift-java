package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class RateLimitException extends InvalidRequestException {
    public RateLimitException(SiftResponse response) {
        super(response);
    }
}
