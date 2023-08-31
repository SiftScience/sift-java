package com.siftscience;

import com.siftscience.model.VerificationSendResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class VerificationSendResponse extends SiftResponse<VerificationSendResponseBody> {
    VerificationSendResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
   public void populateBodyFromJson(String jsonBody) {
        body = VerificationSendResponseBody.fromJson(jsonBody);
    }
}
