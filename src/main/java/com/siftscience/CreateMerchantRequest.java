package com.siftscience;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;


public class CreateMerchantRequest extends SiftMerchantRequest<CreateMerchantResponse> {

    CreateMerchantRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, FieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("psp_management")
                .addPathSegment("merchants");
        return path.build();
    }

    @Override
    CreateMerchantResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new CreateMerchantResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), ""));
    }

}
