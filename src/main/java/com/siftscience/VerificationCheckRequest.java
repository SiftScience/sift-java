package com.siftscience;

import com.siftscience.model.VerificationCheckFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;


/*
* The check call is used for verifying the OTP provided by the end user to Sift.
* Check out https://sift.com/developers/docs/java/verification-api/check for more information on our request/response structure.
*/
public class VerificationCheckRequest extends SiftRequest<VerificationResponse> {

    VerificationCheckRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, VerificationCheckFieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v1")
                .addPathSegment("verification")
                .addPathSegment("check").build();
    }

    @Override
    VerificationResponse buildResponse(Response response, FieldSet requestFields) throws IOException {
        return new VerificationResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }
}
