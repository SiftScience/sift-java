package com.siftscience;

import com.siftscience.model.GlobalProfileResponseBody;
import okhttp3.Response;

import java.io.IOException;

import static com.siftscience.FieldSet.gson;

public class GlobalProfileLookupResponse extends SiftResponse<GlobalProfileResponseBody> {

    public GlobalProfileLookupResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = gson.fromJson(jsonBody, GlobalProfileResponseBody.class);
    }
}
