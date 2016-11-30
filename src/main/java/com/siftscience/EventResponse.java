package com.siftscience;

import com.siftscience.model.*;
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

    public Double getScore(String abuseType) {
        AbuseScore scoreResponse = getAbuseScore(abuseType);
        return scoreResponse != null ? scoreResponse.getScore() : null;
    }

    public List<Reason> getReasons(String abuseType) {
        AbuseScore scoreResponse = getAbuseScore(abuseType);
        return scoreResponse != null ? scoreResponse.getReasons() : null;
    }

    public Map<String, AbuseScore> getAbuseScores() {
        com.siftscience.model.ScoreResponse scoreResponse = getBody().getScoreResponse();
        return scoreResponse != null ? scoreResponse.getScores() : null;
    }

    public AbuseScore getAbuseScore(String abuseType) {
        com.siftscience.model.ScoreResponse scoreResponse = getBody().getScoreResponse();
        return scoreResponse != null && scoreResponse.getScores() != null ?
                scoreResponse.getScores().get(abuseType) : null;
    }

    public List<WorkflowStatus> getWorkflowStatuses() {
        com.siftscience.model.ScoreResponse scoreResponse = getBody().getScoreResponse();
        return scoreResponse != null ? scoreResponse.getWorkflowStatuses() : null;
    }

    public WorkflowStatus getWorkflowStatus(int i) {
        List<WorkflowStatus> workflowStatuses = getWorkflowStatuses();
        return workflowStatuses != null ? workflowStatuses.get(i) : null;
    }
}
