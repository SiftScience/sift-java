package com.siftscience;

import okhttp3.Response;

public class SiftResponse {
    private Response okResponse;

    private int status;
    private String errorMessage;
    private int time;
    private String request;

    public SiftResponse(Response okResponse) {
        this.okResponse = okResponse;
    }

    public int getStatus() {
        return status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getTime() {
        return time;
    }

    void setTime(int time) {
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    void setRequest(String request) {
        this.request = request;
    }
}
