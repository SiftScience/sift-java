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
        return this.getResponseBody().getScores().get(abuseType);
    }

    public Label getLatestLabel(String abuseType) {
        return this.getResponseBody().getLatestLabels().get(abuseType);
    }
}
