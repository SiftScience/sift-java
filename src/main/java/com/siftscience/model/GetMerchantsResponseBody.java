package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMerchantsResponseBody extends MerchantBaseResponseBody<GetMerchantsResponseBody>{
    @Expose @SerializedName("data") private final List<Merchant> merchants;
    @Expose @SerializedName("has_more") private final Boolean hasMore;
    @Expose @SerializedName("total_results") private final Long totalResults;
    @Expose @SerializedName("next_ref") private final String nextRef;

    public GetMerchantsResponseBody(List<Merchant> merchants, boolean hasMore, long totalResults, String nextRef) {
        this.merchants = merchants;
        this.hasMore = hasMore;
        this.totalResults = totalResults;
        this.nextRef = nextRef;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public String getNextRef() {
        return nextRef;
    }

}
