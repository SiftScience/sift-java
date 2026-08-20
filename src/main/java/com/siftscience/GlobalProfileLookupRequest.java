package com.siftscience;

import java.io.IOException;

import com.siftscience.model.GlobalProfileLookupFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class GlobalProfileLookupRequest extends SiftRequest<GlobalProfileLookupResponse> {

    GlobalProfileLookupRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient,
                                GlobalProfileLookupFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("global_profile")
                .addPathSegment("lookup")
                .build();
    }

    @Override
    GlobalProfileLookupResponse buildResponse(okhttp3.Response response, FieldSet requestFields)
            throws IOException {
        return new GlobalProfileLookupResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), ""));
    }
}
