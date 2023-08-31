package com.siftscience;

import com.siftscience.model.VerificationResendResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class VerificationResendResponse extends SiftResponse<VerificationResendResponseBody> {
    VerificationResendResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
   public void populateBodyFromJson(String jsonBody) {
        body = VerificationResendResponseBody.fromJson(jsonBody);
    }
}
