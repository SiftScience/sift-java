package com.siftscience;

import com.siftscience.model.ScoreResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class ScoreResponse extends SiftResponse<ScoreResponseBody> {
    ScoreResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = ScoreResponseBody.fromJson(jsonBody);
    }
}
