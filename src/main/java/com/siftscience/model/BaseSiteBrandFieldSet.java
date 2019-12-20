package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseSiteBrandFieldSet<T extends BaseSiteBrandFieldSet<T>>
    extends EventsApiRequestFieldSet<T> {
    @Expose @SerializedName("$brand_name") private String brandName;
    @Expose @SerializedName("$site_country") private String siteCountry;
    @Expose @SerializedName("$site_domain") private String siteDomain;

    public String getBrandName() {
        return brandName;
    }

    public T setBrandName(String brandName) {
        this.brandName = brandName;
        return (T) this;
    }

    public String getSiteCountry() {
        return siteCountry;
    }

    public T setSiteCountry(String siteCountry) {
        this.siteCountry = siteCountry;
        return (T) this;
    }

    public String getSiteDomain() {
        return siteDomain;
    }

    public T setSiteDomain(String siteDomain) {
        this.siteDomain = siteDomain;
        return (T) this;
    }
}
