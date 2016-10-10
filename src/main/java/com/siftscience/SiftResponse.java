package com.siftscience;

import com.siftscience.model.BaseResponseBody;
import com.sun.istack.internal.NotNull;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public abstract class SiftResponse<T extends BaseResponseBody<T>> {
    private Response okResponse;
    private int time;
    private FieldSet requestBody;
    T body;

    SiftResponse(@NotNull Response okResponse, FieldSet requestBody) throws IOException {
        this.okResponse = okResponse;
        this.requestBody = requestBody;

        ResponseBody rspBody = okResponse.body();
        if (rspBody != null) {
            String bodyString = rspBody.string();
            if (!bodyString.isEmpty()) {
                populateBodyFromJson(bodyString);
            }
        }
    }

    abstract void populateBodyFromJson(String jsonBody);

    public int getHttpStatusCode() {
        return okResponse.code();
    }

    public T getResponseBody() {
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
