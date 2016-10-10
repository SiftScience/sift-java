package com.siftscience;

import com.sun.istack.internal.NotNull;
import okhttp3.Response;

import java.io.IOException;

public class UnlabelResponse extends SiftResponse {
    UnlabelResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }
}
