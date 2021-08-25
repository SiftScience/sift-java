package com.siftscience;

import com.siftscience.model.LabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

/**
 * LabelRequest is the request type for the Sift Labels API.
 * https://siftscience.com/developers/docs/curl/labels-api
 */
public class LabelRequest extends SiftRequest<LabelResponse> {
    LabelRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, LabelFieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v206")
                .addPathSegment("users")
                .addPathSegment(((LabelFieldSet)fieldSet).getUserId())
                .addPathSegment("labels")
                .build();
    }

    @Override
    LabelResponse buildResponse(Response response, FieldSet requestFields) throws IOException {
        return new LabelResponse(response, requestFields);
    }
}
