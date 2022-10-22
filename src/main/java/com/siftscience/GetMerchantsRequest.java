package com.siftscience;

import com.siftscience.model.GetMerchantsFieldSet;
import okhttp3.*;

import java.io.IOException;


public class GetMerchantsRequest extends SiftMerchantRequest<GetMerchantsResponse> {

    GetMerchantsRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    private static String DEFAULT_BATCH_SIZE = "1000";

    public enum Query {
        BATCH_TOKEN("batch_token"),
        BATCH_SIZE("batch_size");

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
        GetMerchantsFieldSet fieldSet = (GetMerchantsFieldSet) this.fieldSet;
        HttpUrl.Builder path = baseUrl.newBuilder("/v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("psp_management")
                .addPathSegment("merchants");

        if (fieldSet.getBatchSize() != null) {
            path.addQueryParameter(Query.BATCH_SIZE.toString(), fieldSet.getBatchSize());
        } else {
            path.addQueryParameter(Query.BATCH_SIZE.toString(), DEFAULT_BATCH_SIZE);
        }

        if (fieldSet.getBatchToken() != null) {
            path.addQueryParameter(Query.BATCH_TOKEN.toString(), String.valueOf(fieldSet.getBatchToken()));
        }

        return path.build();
    }

    @Override
    GetMerchantsResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new GetMerchantsResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
