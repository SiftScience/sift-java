package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRate {
    @Expose @SerializedName("$quote_currency_code") private String quoteCurrencyCode;
    @Expose @SerializedName("$rate") private Float rate;

    public String getQuoteCurrencyCode() {
        return quoteCurrencyCode;
    }

    public ExchangeRate setQuoteCurrencyCode(String quoteCurrencyCode) {
        this.quoteCurrencyCode = quoteCurrencyCode;
        return this;
    }

    public Float getRate() {
        return rate;
    }

    public ExchangeRate setRate(Float rate) {
        this.rate = rate;
        return this;
    }
}
