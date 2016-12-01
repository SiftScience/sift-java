package com.siftscience;

import com.siftscience.model.AbuseScore;
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

    public AbuseScore getScoreResponse(String abuseType) {
        if (this.getBody() != null && this.getBody().getScores() != null) {
            return this.getBody().getScores().get(abuseType);
        }
        return null;
    }

    public Double getScore(String abuseType) {
        AbuseScore abuseScore = getScoreResponse(abuseType);
        if (abuseScore != null) {
            return abuseScore.getScore();
        }
        return null;
    }
}
