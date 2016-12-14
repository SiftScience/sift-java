package com.siftscience;

import com.siftscience.model.DecisionLogJson;
import okhttp3.Response;

import java.io.IOException;

public class ApplyDecisionResponse extends SiftResponse<DecisionLogJson>{

    ApplyDecisionResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = DecisionLogJson.fromJson(jsonBody);
    }

}
