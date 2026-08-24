package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kyc {
    @Expose @SerializedName("$names_match") private Boolean namesMatch;
    @Expose @SerializedName("$kyc_level") private String kycLevel;
    @Expose @SerializedName("$bin_nationality_match") private Boolean binNationalityMatch;
    @Expose @SerializedName("$provider") private String provider;

    public Boolean getNamesMatch() {
        return namesMatch;
    }

    public Kyc setNamesMatch(Boolean namesMatch) {
        this.namesMatch = namesMatch;
        return this;
    }

    public String getKycLevel() {
        return kycLevel;
    }

    public Kyc setKycLevel(String kycLevel) {
        this.kycLevel = kycLevel;
        return this;
    }

    public Boolean getBinNationalityMatch() {
        return binNationalityMatch;
    }

    public Kyc setBinNationalityMatch(Boolean binNationalityMatch) {
        this.binNationalityMatch = binNationalityMatch;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public Kyc setProvider(String provider) {
        this.provider = provider;
        return this;
    }
}
