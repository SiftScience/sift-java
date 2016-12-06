package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDecisionsResponseBody extends BaseResponseBody<GetDecisionsResponseBody>{
    @Expose @SerializedName("decisions") private final List<Decision> decisions;

    public GetDecisionsResponseBody(List<Decision> decisions) {
        this.decisions = decisions;
    }
}
