package com.siftscience;

import com.siftscience.model.GetMerchantFieldSet;
import okhttp3.*;

import java.io.IOException;


public class GetMerchantRequest extends SiftMerchantRequest<GetMerchantResponse> {

    GetMerchantRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
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
