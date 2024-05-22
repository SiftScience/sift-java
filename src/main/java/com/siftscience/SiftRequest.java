package com.siftscience;

import java.io.IOException;

import static com.siftscience.Constants.USER_AGENT_HEADER;
import com.siftscience.exception.InvalidApiKeyException;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.exception.InvalidRequestException;
import com.siftscience.exception.MissingFieldException;
import com.siftscience.exception.RateLimitException;
import com.siftscience.exception.ServerException;
import com.siftscience.exception.SiftException;
import com.siftscience.utils.OkHttpUtils;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * SiftRequest is the base class for all Sift API requests. It implements the `send` method which
 * should be used by all subtypes as it provides standard error handling logic.
 */
public abstract class SiftRequest<T extends SiftResponse> {
    private final String accountId;
    FieldSet fieldSet;
    private HttpClient httpClient;
    private HttpUrl baseUrl;

    protected abstract HttpUrl path(HttpUrl baseUrl);

    public HttpUrl url() {
        return path(baseUrl);
    }

    SiftRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, FieldSet fields) {
        this.baseUrl = baseUrl;
        this.accountId = accountId;
        this.httpClient = httpClient;
        this.fieldSet = fields;
    }

    /**
     * By default, the request is a JSON encoded POST.
     */
    protected void modifyRequestBuilder(Request.Builder builder) {
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }

    abstract T buildResponse(Response response, FieldSet requestFields) throws IOException;

    public T send() throws IOException {
        fieldSet.validate();

        // Ok now that the fieldSet is valid, construct and send the request.
        Request.Builder okRequestBuilder = new Request.Builder().addHeader("User-Agent", USER_AGENT_HEADER).url(this.url());
        modifyRequestBuilder(okRequestBuilder);
        Request request = okRequestBuilder.build();
        T response = buildResponse(httpClient.execute(request), fieldSet);

        // If not successful but no exception happened yet, dig deeper into the response so we
        // can manually throw an appropriate exception.
        if (!response.isOk()) {
            int httpCode = response.getHttpStatusCode();
            Integer siftCode = response.getApiStatus();

            if (httpCode >= 500 && httpCode < 600) {
                throw new ServerException(response);
            } else if (httpCode >= 400 && httpCode < 500) {
                if (siftCode == null) {
                    throw new ServerException(response);
                }
                switch (siftCode) {
                    case -4: case -3: case -2: case -1:
                        throw new ServerException(response);
                    case 51:
                        throw new InvalidApiKeyException(response);
                    case 52: case 53: case 105:
                        throw new InvalidFieldException(response);
                    case 55:
                        throw new MissingFieldException(response);
                    case 56: case 57: case 104:
                        throw new InvalidRequestException(response);
                    case 60:
                        throw new RateLimitException(response);
                }
            }

            throw new SiftException(response);
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
