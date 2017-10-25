package com.siftscience;

import com.google.gson.JsonSyntaxException;
import com.siftscience.exception.ServerException;
import com.siftscience.model.BaseResponseBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * SiftResponse is the base class of all Sift API responses. It is a simple wrapper around the
 * actual OkHttp response.
 */
public abstract class SiftResponse<T extends BaseResponseBody<T>> {
    private Response okResponse;
    private int time;
    private FieldSet requestBody;
    T body;

    SiftResponse(Response okResponse, FieldSet requestBody) throws IOException {
        this.okResponse = okResponse;
        this.requestBody = requestBody;

        ResponseBody rspBody = okResponse.body();
        if (rspBody != null) {
            String bodyString = rspBody.string();
            if (!bodyString.isEmpty()) {
                try {
                    populateBodyFromJson(bodyString);
                } catch (JsonSyntaxException e) {
                    // If parsing the response body as JSON failed for a 5xx, ignore the parse
                    // exception.
                    int code = okResponse.code();
                    if (code < 500 || code >= 600) {
                        throw e;
                    }
                }
            }
        }
    }

    abstract void populateBodyFromJson(String jsonBody);

    public int getHttpStatusCode() {
        return okResponse.code();
    }

    public T getBody() {
        return body;
    }

    public int getTime() {
        return time;
    }

    SiftResponse setTime(int time) {
        this.time = time;
        return this;
    }

    public FieldSet getRequestBody() {
        return requestBody;
    }

    public boolean isOk() {
        return okResponse != null && okResponse.isSuccessful();
    }

    public Integer getApiStatus() {
        if (body != null) {
            return body.getStatus();
        }
        return null;
    }

    public String getApiErrorMessage() {
        if (body != null) {
            return body.getErrorMessage();
        }
        return null;
    }
}
