package com.siftscience;

import com.siftscience.exception.*;
import okhttp3.*;

import java.io.IOException;

/**
 *  It implements the `send` method which
 * should be used by all subtypes as it provides standard error handling logic.
 */
public abstract class SiftMerchantRequest<T extends SiftMerchantResponse> {
    private final String accountId;
    FieldSet fieldSet;
    private OkHttpClient okClient;
    private HttpUrl baseUrl;

    protected abstract HttpUrl path(HttpUrl baseUrl);

    public HttpUrl url() {
        return path(baseUrl);
    }

    SiftMerchantRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        this.baseUrl = baseUrl;
        this.accountId = accountId;
        this.okClient = okClient;
        this.fieldSet = fields;
    }

    /**
     * By default, the request is a JSON encoded POST.
     */
    protected void modifyRequestBuilder(Request.Builder builder) {
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }

    abstract T buildResponse(Response response, FieldSet requestFields) throws IOException;

    public T send() throws IOException {
        fieldSet.validate();

        // Ok now that the fieldSet is valid, construct and send the request.
        Request.Builder okRequestBuilder = new Request.Builder().url(this.url());
        modifyRequestBuilder(okRequestBuilder);
        Request request = okRequestBuilder.build();
        T response = buildResponse(okClient.newCall(request).execute(), fieldSet);

        // If not successful but no exception happened yet, dig deeper into the response so we
        // can manually throw an appropriate exception.
        if (!response.isOk()) {
                throw new MerchantAPIException(response.getApiErrorMessage());
        }

        return response;
    }

    public FieldSet getFieldSet() {
        return fieldSet;
    }

    protected String getAccountId() {
        return accountId;
    }
}
