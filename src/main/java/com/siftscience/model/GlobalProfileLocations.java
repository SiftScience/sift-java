package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GlobalProfileLocations {
    @Expose @SerializedName("unique_billing_addresses") private Long uniqueBillingAddresses;
    @Expose @SerializedName("unique_shipping_addresses") private Long uniqueShippingAddresses;
    @Expose @SerializedName("distinct_countries_count") private Long distinctCountriesCount;
    @Expose @SerializedName("distinct_regions_count") private Long distinctRegionsCount;
    @Expose @SerializedName("location_connected_accounts")
    private List<GlobalProfileLocationAccount> locationConnectedAccounts;
    @Expose @SerializedName("location_last_used_timestamp") private Long locationLastUsedTimestamp;

    public Long getUniqueBillingAddresses() {
        return uniqueBillingAddresses;
    }

    public GlobalProfileLocations setUniqueBillingAddresses(Long uniqueBillingAddresses) {
        this.uniqueBillingAddresses = uniqueBillingAddresses;
        return this;
    }

    public Long getUniqueShippingAddresses() {
        return uniqueShippingAddresses;
    }

    public GlobalProfileLocations setUniqueShippingAddresses(Long uniqueShippingAddresses) {
        this.uniqueShippingAddresses = uniqueShippingAddresses;
        return this;
    }

    public Long getDistinctCountriesCount() {
        return distinctCountriesCount;
    }

    public GlobalProfileLocations setDistinctCountriesCount(Long distinctCountriesCount) {
        this.distinctCountriesCount = distinctCountriesCount;
        return this;
    }

    public Long getDistinctRegionsCount() {
        return distinctRegionsCount;
    }

    public GlobalProfileLocations setDistinctRegionsCount(Long distinctRegionsCount) {
        this.distinctRegionsCount = distinctRegionsCount;
        return this;
    }

    public List<GlobalProfileLocationAccount> getLocationConnectedAccounts() {
        return locationConnectedAccounts;
    }

    public GlobalProfileLocations setLocationConnectedAccounts(
            List<GlobalProfileLocationAccount> locationConnectedAccounts) {
        this.locationConnectedAccounts = locationConnectedAccounts;
        return this;
    }

    public Long getLocationLastUsedTimestamp() {
        return locationLastUsedTimestamp;
    }

    public GlobalProfileLocations setLocationLastUsedTimestamp(Long locationLastUsedTimestamp) {
        this.locationLastUsedTimestamp = locationLastUsedTimestamp;
        return this;
    }
}
