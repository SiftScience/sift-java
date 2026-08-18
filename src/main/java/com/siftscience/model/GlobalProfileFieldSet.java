package com.siftscience.model;

import com.siftscience.FieldSet;

/**
 * Field set for {@code GET /v3/accounts/{accountId}/global_profile/users/{userId}}.
 */
public class GlobalProfileFieldSet extends FieldSet<GlobalProfileFieldSet> {
    private String userId;
    private Boolean globalOnly;
    private Boolean includeOwnData;

    public GlobalProfileFieldSet() {}

    public static GlobalProfileFieldSet fromJson(String json) {
        return gson.fromJson(json, GlobalProfileFieldSet.class);
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
