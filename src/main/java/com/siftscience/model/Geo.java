package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo {
    @Expose @SerializedName("$uuid") private String uuid;
    @Expose @SerializedName("$provider") private String provider;

    public String getUuid() {
        return uuid;
    }

    public Geo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public Geo setProvider(String provider) {
        this.provider = provider;
        return this;
    }
}
