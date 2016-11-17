package com.siftscience;

import com.siftscience.model.LabelResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class LabelResponse extends SiftResponse<LabelResponseBody> {
    LabelResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = LabelResponseBody.fromJson(jsonBody);
    }
}
