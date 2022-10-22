package com.siftscience;

import com.siftscience.model.CreateMerchantResponseBody;
import okhttp3.Response;

import java.io.IOException;

import static com.siftscience.FieldSet.gson;

public class UpdateMerchantResponse extends SiftMerchantResponse<CreateMerchantResponseBody> {


    public UpdateMerchantResponse(Response okResponse, FieldSet requestBody) throws IOException{
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = gson.fromJson(jsonBody, CreateMerchantResponseBody.class);
    }
}
