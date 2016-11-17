package com.siftscience;

import com.siftscience.model.ScoreFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * ScoreRequest is the request type of Sift Score API requests.
 * https://siftscience.com/developers/docs/curl/score-api
 */
public class ScoreRequest extends SiftRequest<ScoreResponse> {
    ScoreRequest(HttpUrl baseUrl, OkHttpClient okClient, ScoreFieldSet fields) {
        super(baseUrl, okClient, fields);
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
        HttpUrl.Builder builder = baseUrl.newBuilder().addPathSegment("v204");
        builder.addPathSegment("score").addPathSegment(scoreFieldSet.getUserId())
                .addQueryParameter("api_key", scoreFieldSet.getApiKey());
        if (scoreFieldSet.getAbuseTypes() != null && scoreFieldSet.getAbuseTypes().size() > 0) {
            String queryParamVal = "";
            for (String abuseType : scoreFieldSet.getAbuseTypes()) {
                queryParamVal += (abuseType + ",");
            }
            builder.addQueryParameter("abuse_types",
                    queryParamVal.substring(0, queryParamVal.length() - 1));
        }
        return builder.build();
    }
}
