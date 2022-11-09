package com.siftscience.exception;

import com.siftscience.SiftMerchantResponse;

public class MerchantAPIException extends RuntimeException {
    private SiftMerchantResponse response;

    public MerchantAPIException(String msg) {
        super(msg);
    }

    public MerchantAPIException(SiftMerchantResponse response) {
        super(responseErrorMessage(response));
        this.response = response;
    }

    public SiftMerchantResponse getSiftResponse() {
        return this.response;
    }

    private static String responseErrorMessage(SiftMerchantResponse response) {
        if (response == null || response.getApiErrorMessage() == null) {
            return "Unexpected API error.";
        }
        return response.getApiErrorMessage();
    }

    public String getApiErrorMessage() {
        return responseErrorMessage(response);
    }
}
