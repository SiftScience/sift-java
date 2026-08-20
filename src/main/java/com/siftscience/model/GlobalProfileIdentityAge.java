package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalProfileIdentityAge {
    @Expose @SerializedName("oldest_account_age_timestamp") private Long oldestAccountAgeTimestamp;
    @Expose @SerializedName("newest_account_age_timestamp") private Long newestAccountAgeTimestamp;
    @Expose @SerializedName("average_account_age_timestamp") private Long averageAccountAgeTimestamp;

    public Long getOldestAccountAgeTimestamp() {
        return oldestAccountAgeTimestamp;
    }

    public GlobalProfileIdentityAge setOldestAccountAgeTimestamp(Long oldestAccountAgeTimestamp) {
        this.oldestAccountAgeTimestamp = oldestAccountAgeTimestamp;
        return this;
    }

    public Long getNewestAccountAgeTimestamp() {
        return newestAccountAgeTimestamp;
    }

    public GlobalProfileIdentityAge setNewestAccountAgeTimestamp(Long newestAccountAgeTimestamp) {
        this.newestAccountAgeTimestamp = newestAccountAgeTimestamp;
        return this;
    }

    public Long getAverageAccountAgeTimestamp() {
        return averageAccountAgeTimestamp;
    }

    public GlobalProfileIdentityAge setAverageAccountAgeTimestamp(Long averageAccountAgeTimestamp) {
        this.averageAccountAgeTimestamp = averageAccountAgeTimestamp;
        return this;
    }
}
