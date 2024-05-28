package com.siftscience;

import java.io.IOException;

import com.siftscience.model.LabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * LabelRequest is the request type for the Sift Labels API.
 * https://siftscience.com/developers/docs/curl/labels-api
 */
public class LabelRequest extends SiftRequest<LabelResponse> {
    LabelRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, LabelFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v205")
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
