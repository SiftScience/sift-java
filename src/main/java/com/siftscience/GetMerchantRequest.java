package com.siftscience;

import java.io.IOException;

import com.siftscience.model.GetMerchantFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;


public class GetMerchantRequest extends SiftMerchantRequest<GetMerchantResponse> {

    GetMerchantRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, FieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        GetMerchantFieldSet fieldSet = (GetMerchantFieldSet) this.fieldSet;
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("psp_management")
                .addPathSegment("merchants")
                .addPathSegment(fieldSet.getMerchantId());
        return path.build();
    }

    @Override
    GetMerchantResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new GetMerchantResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
