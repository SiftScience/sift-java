package com.siftscience;

import java.io.IOException;

import com.siftscience.model.ScoreFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ScoreRequest is the request type of Sift Score API requests.
 * https://siftscience.com/developers/docs/curl/score-api
 */
public class ScoreRequest extends SiftRequest<ScoreResponse> {
    ScoreRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, ScoreFieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    /**
     * Use the GET HTTP method instead of the default POST.
     */
    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        builder.get();
    }

    @Override
    ScoreResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new ScoreResponse(response, requestFields);
    }

    /**
     * For score requests, the api key and abuse types are encoded into the URL as query params
     * because there is no request body.
     */
    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        ScoreFieldSet scoreFieldSet = (ScoreFieldSet)fieldSet;
        HttpUrl.Builder builder = baseUrl.newBuilder().addPathSegment("v205");
        builder.addPathSegment("score").addPathSegment(scoreFieldSet.getUserId())
                .addQueryParameter("api_key", scoreFieldSet.getApiKey());
        if (scoreFieldSet.getAbuseTypes() != null && scoreFieldSet.getAbuseTypes().size() > 0) {
            builder.addQueryParameter("abuse_types",
                StringUtils.joinWithComma(scoreFieldSet.getAbuseTypes()));
        }
        if (scoreFieldSet.isReturnScorePercentiles()) {
            builder.addQueryParameter("fields", "score_percentiles");
        }
        return builder.build();
    }
}
