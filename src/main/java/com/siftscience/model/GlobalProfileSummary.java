package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Summary information about a user's global profile, as returned by the Global Profile API.
 */
public class GlobalProfileSummary {
    @Expose @SerializedName("identity_found") private Boolean identityFound;
    @Expose @SerializedName("has_links") private Boolean hasLinks;
    @Expose @SerializedName("link_count") private Long linkCount;
    @Expose @SerializedName("linked_accounts_count_per_industry")
    private Map<String, Long> linkedAccountsCountPerIndustry;

    public Boolean getIdentityFound() {
        return identityFound;
    }

    public GlobalProfileSummary setIdentityFound(Boolean identityFound) {
        this.identityFound = identityFound;
        return this;
    }

    public Boolean getHasLinks() {
        return hasLinks;
    }

    public GlobalProfileSummary setHasLinks(Boolean hasLinks) {
        this.hasLinks = hasLinks;
        return this;
    }

    public Long getLinkCount() {
        return linkCount;
    }

    public GlobalProfileSummary setLinkCount(Long linkCount) {
        this.linkCount = linkCount;
        return this;
    }

    public Map<String, Long> getLinkedAccountsCountPerIndustry() {
        return linkedAccountsCountPerIndustry;
    }

    public GlobalProfileSummary setLinkedAccountsCountPerIndustry(
            Map<String, Long> linkedAccountsCountPerIndustry) {
        this.linkedAccountsCountPerIndustry = linkedAccountsCountPerIndustry;
        return this;
    }
}
