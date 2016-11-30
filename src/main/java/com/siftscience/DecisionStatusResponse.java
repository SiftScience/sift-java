package com.siftscience;

import com.siftscience.model.Decision;
import com.siftscience.model.DecisionStatusResponseBody;
import com.siftscience.model.LatestDecision;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class DecisionStatusResponse extends SiftResponse<DecisionStatusResponseBody> {
    DecisionStatusResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = DecisionStatusResponseBody.fromJson(jsonBody);
    }

    public Map<String, LatestDecision> getDecisionStatuses() {
        return body.getDecisions();
    }

    public LatestDecision getDecisionStatus(String key) {
        return getDecisionStatuses().get(key);
    }

    public Decision getDecision(String key) {
        return getDecisionStatus(key).getDecision();
    }
}
