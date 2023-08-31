package com.siftscience;

import com.siftscience.model.VerificationCheckResponseBody;
import com.siftscience.model.VerificationResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class VerificationCheckResponse extends SiftResponse<VerificationCheckResponseBody> {
    VerificationCheckResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
   public void populateBodyFromJson(String jsonBody) {
        body = VerificationCheckResponseBody.fromJson(jsonBody);
    }
}
