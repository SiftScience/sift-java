package com.siftscience;

import com.siftscience.model.LabelResponseFieldSet;
import okhttp3.Response;

import java.io.IOException;

public class LabelResponse extends SiftResponse<LabelResponseFieldSet> {
    LabelResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = LabelResponseFieldSet.fromJson(jsonBody);
    }
}
