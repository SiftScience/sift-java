package com.siftscience;

import com.siftscience.model.AbuseScore;
import com.siftscience.model.EventResponseBody;
import com.siftscience.model.Label;
import com.siftscience.model.WorkflowStatus;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EventResponse extends SiftResponse<EventResponseBody> {
    EventResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = EventResponseBody.fromJson(jsonBody);
    }

    public Map<String, AbuseScore> getScores() {
        return this.getResponseBody().getScoreResponse().getScores();
    }

    public AbuseScore getScore(String abuseType) {
        return this.getResponseBody().getScoreResponse().getScores().get(abuseType);
    }

    public List<WorkflowStatus> getWorkflowStatuses() {
        return this.getResponseBody().getScoreResponse().getWorkflowStatuses();
    }

    public WorkflowStatus getWorkflowStatus(int i) {
        return this.getResponseBody().getScoreResponse().getWorkflowStatuses().get(i);
    }
}
