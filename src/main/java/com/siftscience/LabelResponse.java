package com.siftscience;

import com.siftscience.model.LabelResponseFieldSet;
import com.sun.istack.internal.NotNull;
import okhttp3.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class LabelResponse extends SiftResponse<LabelResponseFieldSet> {
    LabelResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = LabelResponseFieldSet.fromJson(jsonBody);
    }
}
