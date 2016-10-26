package com.siftscience;

import com.siftscience.model.DecisionStatusResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class DecisionStatusResponse extends SiftResponse<DecisionStatusResponseBody> {
    DecisionStatusResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = DecisionStatusResponseBody.fromJson(jsonBody);
    }
}
