package com.siftscience;

import java.io.IOException;

import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.*;

public class ApplyDecisionRequest extends SiftRequest<ApplyDecisionResponse>{

    private String apiKey;

    ApplyDecisionRequest(HttpUrl baseUrl, OkHttpClient okClient, ApplyDecisionFieldSet fields, String apiKey) {
        super(baseUrl, okClient, fields);
        this.apiKey = apiKey;
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl;
    }

    @Override
    ApplyDecisionResponse buildResponse(okhttp3.Response response, FieldSet requestFields) throws IOException {
        return new ApplyDecisionResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(apiKey,""));
    }

}
