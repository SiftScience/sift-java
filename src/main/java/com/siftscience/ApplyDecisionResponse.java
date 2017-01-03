package com.siftscience;

import com.siftscience.model.DecisionLog;
import okhttp3.Response;

import java.io.IOException;

public class ApplyDecisionResponse extends SiftResponse<DecisionLog>{

    ApplyDecisionResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = DecisionLog.fromJson(jsonBody);
    }

    public DecisionLog getDecisionLog() {
        return body;
    }

}
