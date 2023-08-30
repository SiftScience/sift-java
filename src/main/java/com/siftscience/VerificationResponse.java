package com.siftscience;

import com.siftscience.model.VerificationResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class VerificationResponse extends SiftResponse<VerificationResponseBody> {
    VerificationResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
   public void populateBodyFromJson(String jsonBody) {
        body = VerificationResponseBody.fromJson(jsonBody);
    }
}
