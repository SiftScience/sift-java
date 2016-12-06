package com.siftscience;

import com.siftscience.model.GetDecisionFieldSet;
import okhttp3.*;

import java.io.IOException;

public class GetDecisionsRequest extends SiftRequest<GetDecisionsResponse> {
    GetDecisionsRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(((GetDecisionFieldSet) fieldSet).getAccountId())
                .addPathSegment("decisions")
                .build();
    }

    @Override
    GetDecisionsResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new GetDecisionsResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
