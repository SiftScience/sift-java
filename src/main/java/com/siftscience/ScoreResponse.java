package com.siftscience;

import com.siftscience.model.AbuseScore;
import com.siftscience.model.Label;
import com.siftscience.model.ScoreResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class ScoreResponse extends SiftResponse<ScoreResponseBody> {
    ScoreResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = ScoreResponseBody.fromJson(jsonBody);
    }

    public AbuseScore getScore(String abuseType) {
        if (this.getResponseBody() != null && this.getResponseBody().getScores() != null) {
            return this.getResponseBody().getScores().get(abuseType);
        }
        return null;
    }

    public Label getLatestLabel(String abuseType) {
        if (this.getResponseBody() != null && this.getResponseBody().getLatestLabels() != null) {
            return this.getResponseBody().getLatestLabels().get(abuseType);
        }
        return null;
    }
}
