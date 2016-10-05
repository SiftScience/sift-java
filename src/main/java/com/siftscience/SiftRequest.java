package com.siftscience;

import com.siftscience.model.FieldSet;
import okhttp3.*;

import java.io.IOException;

public abstract class SiftRequest {
    private FieldSet fieldSet;
    private OkHttpClient okClient;
    private HttpUrl baseUrl;

    protected abstract HttpUrl path(HttpUrl baseUrl);

    public HttpUrl url() {
        return path(baseUrl.newBuilder().addPathSegment("v204").build());
    }

    SiftRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        this.baseUrl = baseUrl;
        this.okClient = okClient;
        this.fieldSet = fields;
    }

    public SiftResponse send() throws IOException {
        return new SiftResponse(okClient.newCall(new Request.Builder()
                .url(this.url())
                .post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()))
                .build()
        ).execute());
    }

    public FieldSet getFieldSet() {
        return fieldSet;
    }
}
