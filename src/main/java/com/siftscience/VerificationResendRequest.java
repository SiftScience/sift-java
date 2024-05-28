package com.siftscience;

import java.io.IOException;

import com.siftscience.model.VerificationResendFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
* The resend call generates a new OTP and sends it to the original recipient with the same settings.
* Check out https://sift.com/developers/docs/java/verification-api/resend for more information on our request/response structuree.
* */
public class VerificationResendRequest extends SiftRequest<VerificationResendResponse> {

    VerificationResendRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, VerificationResendFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v1")
                .addPathSegment("verification")
                .addPathSegment("resend").build();
    }

    @Override
    VerificationResendResponse buildResponse(Response response, FieldSet requestFields) throws IOException {
        return new VerificationResendResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }
}
