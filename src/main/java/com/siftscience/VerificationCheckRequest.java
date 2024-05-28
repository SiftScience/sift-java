package com.siftscience;

import java.io.IOException;

import com.siftscience.model.VerificationCheckFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
* The check call is used for verifying the OTP provided by the end user to Sift.
* Check out https://sift.com/developers/docs/java/verification-api/check for more information on our request/response structure.
*/
public class VerificationCheckRequest extends SiftRequest<VerificationCheckResponse> {

    VerificationCheckRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, VerificationCheckFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v1")
                .addPathSegment("verification")
                .addPathSegment("check").build();
    }

    @Override
    VerificationCheckResponse buildResponse(Response response, FieldSet requestFields) throws IOException {
        return new VerificationCheckResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }
}
