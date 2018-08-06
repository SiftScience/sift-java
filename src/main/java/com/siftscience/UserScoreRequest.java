package com.siftscience;

import com.siftscience.model.UserScoreFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * UserScoreRequest is the request type of Sift User Score API requests.
 *
 * This includes:
 * 1. The get user score API, which returns the latest score(s) computed for a user.
 *    See details here: https://siftscience.com/developers/docs/java/score-api/get-score
 * 2. The rescore user API, which recomputes scores for a user.
 *    See details here: https://siftscience.com/developers/docs/java/score-api/rescore
 */
public class UserScoreRequest extends SiftRequest<EntityScoreResponse> {
    UserScoreRequest(HttpUrl baseUrl, OkHttpClient okClient, UserScoreFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    /**
     * Use a POST request if this is a rescore user request, otherwise use a GET request.
     */
    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        if (((UserScoreFieldSet)fieldSet).getRescoreUser()) {
            builder.post(RequestBody.create(null, new byte[0]));
        } else {
            builder.get();
        }
    }

    @Override
    EntityScoreResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new EntityScoreResponse(response, requestFields);
    }

    /**
     * For user score requests, the api key and abuse types are encoded into the URL as query params
     */
    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        UserScoreFieldSet userSoreFieldSet = (UserScoreFieldSet)fieldSet;
        HttpUrl.Builder builder = baseUrl.newBuilder().addPathSegment("v205");
        builder.addPathSegment("users")
                .addPathSegment(userSoreFieldSet.getUserId())
                .addPathSegment("score")
                .addQueryParameter("api_key", userSoreFieldSet.getApiKey());
        if (userSoreFieldSet.getAbuseTypes() != null && userSoreFieldSet.getAbuseTypes().size() > 0) {
            String queryParamVal = "";
            for (String abuseType : userSoreFieldSet.getAbuseTypes()) {
                queryParamVal += (abuseType + ",");
            }
            builder.addQueryParameter("abuse_types",
                    queryParamVal.substring(0, queryParamVal.length() - 1));
        }
        return builder.build();
    }
}
