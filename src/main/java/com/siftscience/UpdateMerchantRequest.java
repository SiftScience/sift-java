package com.siftscience;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Credentials;
import okhttp3.RequestBody;
import okhttp3.MediaType;


import java.io.IOException;


public class UpdateMerchantRequest extends SiftMerchantRequest<UpdateMerchantResponse> {

    private final String merchantId;

    UpdateMerchantRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, FieldSet fields, String merchantId) {
        super(baseUrl, accountId, httpClient, fields);
        this.merchantId = merchantId;
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("psp_management")
                .addPathSegment("merchants")
                .addPathSegments(getMerchantId());
        return path.build();
    }

    @Override
    UpdateMerchantResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new UpdateMerchantResponse(response, requestFields);
    }

    public String getMerchantId() {
        return merchantId;
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.put(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }
}
