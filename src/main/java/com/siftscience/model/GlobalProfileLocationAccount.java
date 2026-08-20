package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A single connected account location entry as returned within a Global Profile API response's
 * `locations.location_connected_accounts` list.
 */
public class GlobalProfileLocationAccount {
    @Expose @SerializedName("city") private String city;
    @Expose @SerializedName("region") private String region;
    @Expose @SerializedName("country") private String country;

    public String getCity() {
        return city;
    }

    public GlobalProfileLocationAccount setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public GlobalProfileLocationAccount setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public GlobalProfileLocationAccount setCountry(String country) {
        this.country = country;
        return this;
    }
}
