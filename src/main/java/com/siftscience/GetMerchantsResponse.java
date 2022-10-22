package com.siftscience;

import com.siftscience.model.GetMerchantsResponseBody;
import okhttp3.Response;

import java.io.IOException;

import static com.siftscience.FieldSet.gson;

public class GetMerchantsResponse extends SiftMerchantResponse<GetMerchantsResponseBody> {

    public GetMerchantsResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = gson.fromJson(jsonBody, GetMerchantsResponseBody.class);
    }
}
