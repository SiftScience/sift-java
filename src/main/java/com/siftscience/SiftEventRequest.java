package com.siftscience;

import com.siftscience.model.FieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class SiftEventRequest extends SiftRequest {
    public SiftEventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("events")
                .build();
    }
}
