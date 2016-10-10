package com.siftscience;

import com.siftscience.model.LabelFieldSet;
import com.sun.istack.internal.NotNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

public class LabelRequest extends SiftRequest<LabelResponse> {

    LabelRequest(HttpUrl baseUrl, OkHttpClient okClient, LabelFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("users")
                .addPathSegment(((LabelFieldSet)fieldSet).getUserId())
                .addPathSegment("labels")
                .build();
    }

    @Override
    LabelResponse buildResponse(@NotNull Response response, FieldSet requestFields)
            throws IOException {
        return new LabelResponse(response, requestFields);
    }
}
