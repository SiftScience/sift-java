package com.siftscience;

import com.sun.istack.internal.NotNull;
import okhttp3.Response;

import java.io.IOException;

public class EventResponse extends SiftResponse {
    EventResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }
}
