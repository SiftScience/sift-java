package com.siftscience;

import com.siftscience.model.UnlabelFieldSet;
import okhttp3.*;

import java.io.IOException;

/**
 * UnlabelRequest is the request type to use for unlabeling a user via the Labels API.
 * https://siftscience.com/developers/docs/curl/labels-api/unlabel-user
 */
public class UnlabelRequest extends SiftRequest<UnlabelResponse> {
    UnlabelRequest(HttpUrl baseUrl, OkHttpClient okClient, UnlabelFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    /**
     * For unlabeling, the api key and abuse types are encoded into the URL since there is no
     * request body.
     */
    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        UnlabelFieldSet unlabelFields = (UnlabelFieldSet)fieldSet;
        HttpUrl.Builder builder = baseUrl.newBuilder()
                .addPathSegment("v204")
                .addPathSegment("users")
                .addPathSegment(unlabelFields.getUserId())
                .addPathSegment("labels");
        builder.addQueryParameter("api_key", unlabelFields.getApiKey());
        if (unlabelFields.getAbuseType() != null) {
            builder.addQueryParameter("abuse_type", unlabelFields.getAbuseType());
        }
        return builder.build();
    }

    /**
     * Use the DELETE HTTP method instead of the default POST.
     */
    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        builder.delete();
    }

    @Override
    UnlabelResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new UnlabelResponse(response, requestFields);
    }
}
