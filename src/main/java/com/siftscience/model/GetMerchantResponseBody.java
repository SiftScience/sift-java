package com.siftscience.model;

import com.google.gson.annotations.Expose;

public class GetMerchantResponseBody extends BaseResponseBody<GetMerchantResponseBody>{

    @Expose private final Merchant merchant;

    public GetMerchantResponseBody(Merchant merchant) {
        this.merchant = merchant;
    }
}
