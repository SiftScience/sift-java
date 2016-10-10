package com.siftscience;

import com.sun.istack.internal.NotNull;
import okhttp3.Response;

import java.io.IOException;

public class ScoreResponse extends SiftResponse {
    ScoreResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }
}
