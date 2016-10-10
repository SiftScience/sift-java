package com.siftscience;

import com.sun.istack.internal.NotNull;
import okhttp3.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class UnlabelResponse extends SiftResponse {
    UnlabelResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    /**
     * This should never be called because the unlabel API doesn't return a response body.
     *
     * @param jsonBody
     */
    @Override
    void populateBodyFromJson(String jsonBody) {
        throw new NotImplementedException();
    }
}
