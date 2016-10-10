package com.siftscience;

import com.siftscience.model.EventResponseBody;
import okhttp3.Response;

import java.io.IOException;

public class EventResponse extends SiftResponse<EventResponseBody> {
    EventResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    void populateBodyFromJson(String jsonBody) {
        body = EventResponseBody.fromJson(jsonBody);
    }
}
