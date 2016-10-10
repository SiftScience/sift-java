package com.siftscience;

import com.siftscience.model.ResponseFieldSet;
import com.sun.istack.internal.NotNull;
import okhttp3.Response;

import java.io.IOException;

public abstract class SiftResponse {
    private Response okResponse;
    private int time;
    private FieldSet requestBody;
    private ResponseFieldSet body;

    SiftResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        this.okResponse = okResponse;
        if (okResponse.body() != null) {
            this.body = ResponseFieldSet.fromJson(okResponse.body().string());
        }
        this.requestBody = requestBody;
    }

    public int getHttpStatusCode() {
        return okResponse.code();
    }

    public ResponseFieldSet getResponseBody() {
        return body;
    }

    public String getErrorMessage() {
        if (body != null) {
            return body.getErrorMessage();
        }
        return null;
    }

    public int getTime() {
        return time;
    }

    void setTime(int time) {
        this.time = time;
    }

    public FieldSet getRequestBody() {
        return requestBody;
    }

    SiftResponse setRequestBody(FieldSet requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public boolean isSuccessful() {
        return okResponse.isSuccessful();
    }

    Response getOkResponse() {
        return okResponse;
    }

    public Integer getSiftStatusCode() {
        if (body != null) {
            return body.getStatus();
        }
        return null;
    }
}
