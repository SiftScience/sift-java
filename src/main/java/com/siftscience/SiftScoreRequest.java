package com.siftscience;

import com.siftscience.model.ScoreFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SiftScoreRequest extends SiftRequest {
    SiftScoreRequest(HttpUrl baseUrl, OkHttpClient okClient, ScoreFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        builder.get();
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        ScoreFieldSet scoreFieldSet = (ScoreFieldSet)fieldSet;
        HttpUrl.Builder builder = baseUrl.newBuilder();
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
