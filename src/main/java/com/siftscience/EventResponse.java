package com.siftscience;

import com.siftscience.model.AbuseScore;
import com.siftscience.model.EventResponseBody;
import com.siftscience.model.Label;
import com.siftscience.model.WorkflowStatus;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class EventResponse extends SiftResponse<EventResponseBody> {
    EventResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = EventResponseBody.fromJson(jsonBody);
    }

    public AbuseScore getScore(String abuseType) {
        return this.getResponseBody().getScoreResponse().getScores().get(abuseType);
    }

    public List<WorkflowStatus> getWorkflowStatuses() {
        return this.getResponseBody().getScoreResponse().getWorkflowStatuses();
    }
}
