package com.siftscience;

import com.siftscience.model.GetDecisionFieldSet;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetDecisionsRequest extends SiftRequest<GetDecisionsResponse> {

    GetDecisionsRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    public enum Query {
        ENTITY_TYPE("entity_type"),
        LIMIT("limit"),
        FROM("from"),
        ABUSE_TYPES("abuse_types");

        private final String value;

        Query(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        GetDecisionFieldSet fieldSet = (GetDecisionFieldSet) this.fieldSet;
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("decisions");

        if (fieldSet.getEntityType() != null) {
            path.addQueryParameter(Query.ENTITY_TYPE.toString(), fieldSet.getEntityType().name());
        }
        if (fieldSet.getLimit() != null) {
            path.addQueryParameter(Query.LIMIT.toString(), String.valueOf(fieldSet.getLimit()));
        }
        if (fieldSet.getFrom() != null) {
            path.addQueryParameter(Query.FROM.toString(), String.valueOf(fieldSet.getFrom()));
        }
        if (fieldSet.getAbuseTypes() != null && !fieldSet.getAbuseTypes().isEmpty()) {
            path.addQueryParameter(Query.ABUSE_TYPES.toString(), StringUtils.joinWithComma(fieldSet.getAbuseTypes()));
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
