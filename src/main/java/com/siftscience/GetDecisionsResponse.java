package com.siftscience;

import com.siftscience.SiftRequest;
import com.siftscience.model.GetDecisionsResponseBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

import static com.siftscience.FieldSet.gson;

public class GetDecisionsResponse extends SiftResponse<GetDecisionsResponseBody>{

    public GetDecisionsResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = gson.fromJson(jsonBody, GetDecisionsResponseBody.class);
    }
}
