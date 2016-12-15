package com.siftscience;

import com.siftscience.model.GetDecisionFieldSet;

import java.io.IOException;
import com.google.common.base.Joiner;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetDecisionsRequest extends SiftRequest<GetDecisionsResponse> {
    private final Joiner joiner = Joiner.on(",");

    GetDecisionsRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        GetDecisionFieldSet fieldSet = (GetDecisionFieldSet) this.fieldSet;
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(fieldSet.getAccountId())
                .addPathSegment("decisions");

        if (fieldSet.getEntityType() != null) {
            path.addQueryParameter("entity_type", fieldSet.getEntityType().name());
        }
        if (fieldSet.getLimit() != null) {
            path.addQueryParameter("limit", String.valueOf(fieldSet.getLimit()));
        }
        if (fieldSet.getCreatedBefore() != null) {
            path.addQueryParameter("created_before", String.valueOf(fieldSet.getCreatedBefore()));
        }
        if (fieldSet.getAbuseTypes() != null && !fieldSet.getAbuseTypes().isEmpty()) {
            path.addQueryParameter("abuse_types", joiner.join(fieldSet.getAbuseTypes()));
        }

        return path.build();
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
