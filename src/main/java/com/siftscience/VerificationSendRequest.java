package com.siftscience;

import com.siftscience.model.VerificationSendFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

/**
*The send call triggers the generation of an OTP code that is stored by Sift
* and email/sms the code to the user.
* Check out https://sift.com/developers/docs/java/verification-api/send for more information on our request/response structure.
* */
public class VerificationSendRequest  extends SiftRequest<VerificationSendResponse> {

    VerificationSendRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, VerificationSendFieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v1")
                .addPathSegment("verification")
                .addPathSegment("send").build();
    }

    @Override
    VerificationSendResponse buildResponse(Response response, FieldSet requestFields) throws IOException {
        return new VerificationSendResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
        builder.post(RequestBody.create(MediaType.parse("application/json"), fieldSet.toJson()));
    }
}
