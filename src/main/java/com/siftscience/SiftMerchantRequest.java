package com.siftscience;

import com.siftscience.exception.MerchantAPIException;
import com.siftscience.utils.OkHttpUtils;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Credentials;
import okhttp3.RequestBody;
import okhttp3.MediaType;

import java.io.IOException;

import static com.siftscience.Constants.USER_AGENT_HEADER;

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

        Request.Builder okRequestBuilder =
            new Request.Builder().addHeader("User-Agent", USER_AGENT_HEADER).url(this.url());
        modifyRequestBuilder(okRequestBuilder);
        Request request = okRequestBuilder.build();
        T response = buildResponse(OkHttpUtils.execute(request, okClient), fieldSet);

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
