package com.siftscience;

import com.siftscience.model.UpdateMerchantFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;


public class UpdateMerchantRequest extends SiftMerchantRequest<UpdateMerchantResponse> {

    private final String merchantId;

    UpdateMerchantRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields, String merchantId) {
        super(baseUrl, accountId, okClient, fields);
        this.merchantId = merchantId;
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
     UpdateMerchantFieldSet fieldSet = (UpdateMerchantFieldSet) this.fieldSet;
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

}
