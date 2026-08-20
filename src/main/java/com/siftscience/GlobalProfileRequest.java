package com.siftscience;

import java.io.IOException;

import com.siftscience.model.GlobalProfileFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class GlobalProfileRequest extends SiftRequest<GlobalProfileResponse> {

    GlobalProfileRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient,
                         GlobalProfileFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    public enum Query {
        GLOBAL_ONLY("global_only"),
        INCLUDE_OWN_DATA("include_own_data");

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
        GlobalProfileFieldSet fieldSet = (GlobalProfileFieldSet) this.fieldSet;
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("global_profile")
                .addPathSegment("users")
                .addPathSegment(fieldSet.getUserId());

        if (fieldSet.getGlobalOnly() != null) {
            path.addQueryParameter(Query.GLOBAL_ONLY.toString(),
                    String.valueOf(fieldSet.getGlobalOnly()));
        }
        if (fieldSet.getIncludeOwnData() != null) {
            path.addQueryParameter(Query.INCLUDE_OWN_DATA.toString(),
                    String.valueOf(fieldSet.getIncludeOwnData()));
        }

        return path.build();
    }

    @Override
    GlobalProfileResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new GlobalProfileResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
