package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;
import com.siftscience.exception.MissingFieldException;

/**
 * Field set for {@code GET /v3/accounts/{accountId}/global_profile/users/{userId}}.
 */
public class GlobalProfileFieldSet extends FieldSet<GlobalProfileFieldSet> {
    @Expose @SerializedName("user_id") private String userId;
    @Expose @SerializedName("global_only") private Boolean globalOnly;
    @Expose @SerializedName("include_own_data") private Boolean includeOwnData;

    public GlobalProfileFieldSet() {}

    public static GlobalProfileFieldSet fromJson(String json) {
        return gson.fromJson(json, GlobalProfileFieldSet.class);
    }

    @Override
    public void validate() {
        super.validate();
        if (userId == null || userId.isEmpty()) {
            throw new MissingFieldException("'userId' is required for a global profile request.");
        }
    }

    public String getUserId() {
        return userId;
    }

    public GlobalProfileFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * If true, excludes the requesting tenant's own network connections from the response.
     * Defaults to false server-side if not set.
     */
    public Boolean getGlobalOnly() {
        return globalOnly;
    }

    public GlobalProfileFieldSet setGlobalOnly(Boolean globalOnly) {
        this.globalOnly = globalOnly;
        return this;
    }

    /**
     * If true, includes the requested user's own feature values in the response. Defaults to
     * true server-side if not set.
     */
    public Boolean getIncludeOwnData() {
        return includeOwnData;
    }

    public GlobalProfileFieldSet setIncludeOwnData(Boolean includeOwnData) {
        this.includeOwnData = includeOwnData;
        return this;
    }
}
