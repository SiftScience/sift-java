package com.siftscience;

import com.siftscience.model.LabelFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class LabelRequest extends SiftRequest {

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
}
