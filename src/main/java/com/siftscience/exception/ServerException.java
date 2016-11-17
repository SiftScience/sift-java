package com.siftscience.exception;

import com.siftscience.SiftResponse;

public class ServerException extends SiftException {
    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(SiftResponse response) {
        super(response);
    }
}
