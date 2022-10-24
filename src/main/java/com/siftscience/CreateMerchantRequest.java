package com.siftscience;

import okhttp3.*;

import java.io.IOException;


public class CreateMerchantRequest extends SiftMerchantRequest<CreateMerchantResponse> {

    CreateMerchantRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
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
