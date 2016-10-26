package com.siftscience;

import com.siftscience.model.DecisionStatusFieldSet;
import okhttp3.*;

import java.io.IOException;

public class DecisionStatusRequest extends SiftRequest<DecisionStatusResponse> {
    DecisionStatusRequest(HttpUrl baseUrl, OkHttpClient okClient, DecisionStatusFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("v3")
                .addPathSegment("accounts")
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getAccountId())
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntity())
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntityId())
                .addPathSegment("decisions")
                .build();
        return url;
    }

    @Override
    DecisionStatusResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new DecisionStatusResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
