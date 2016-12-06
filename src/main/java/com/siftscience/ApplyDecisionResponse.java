package com.siftscience;

import com.siftscience.model.ApplyDecisionResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class ApplyDecisionResponse extends SiftResponse<ApplyDecisionResponseBody>{

    ApplyDecisionResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = ApplyDecisionResponseBody.fromJson(jsonBody);
    }

}
