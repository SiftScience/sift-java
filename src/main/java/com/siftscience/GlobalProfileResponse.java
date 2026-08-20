package com.siftscience;

import com.siftscience.model.GlobalProfileResponseBody;
import okhttp3.Response;

import java.io.IOException;

import static com.siftscience.FieldSet.gson;

public class GlobalProfileResponse extends SiftResponse<GlobalProfileResponseBody> {

    public GlobalProfileResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = gson.fromJson(jsonBody, GlobalProfileResponseBody.class);
    }
}
