package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response body for the Global Profile API, returned by both the
 * {@code GET /v3/accounts/{accountId}/global_profile/users/{userId}} and
 * {@code POST /v3/accounts/{accountId}/global_profile/lookup} endpoints.
 *
 * When {@code profile_summary.identity_found} is {@code false}, every field other than
 * {@code profile_summary} will be {@code null}.
 */
public class GlobalProfileResponseBody extends BaseResponseBody<GlobalProfileResponseBody> {
    @Expose @SerializedName("error_code") private Integer errorCode;
    @Expose @SerializedName("lookback_months") private Integer lookbackMonths;
    @Expose @SerializedName("profile_summary") private GlobalProfileSummary profileSummary;
    @Expose @SerializedName("identity_age") private GlobalProfileIdentityAge identityAge;
    @Expose @SerializedName("user_decisions") private GlobalProfileUserDecisions userDecisions;
    @Expose @SerializedName("chargebacks") private GlobalProfileChargebacks chargebacks;
    @Expose @SerializedName("orders") private GlobalProfileOrders orders;
    @Expose @SerializedName("transactions") private GlobalProfileTransactions transactions;
    @Expose @SerializedName("locations") private GlobalProfileLocations locations;

    public Integer getErrorCode() {
        return errorCode;
    }

    public GlobalProfileResponseBody setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Integer getLookbackMonths() {
        return lookbackMonths;
    }

    public GlobalProfileResponseBody setLookbackMonths(Integer lookbackMonths) {
        this.lookbackMonths = lookbackMonths;
        return this;
    }

    public GlobalProfileSummary getProfileSummary() {
        return profileSummary;
    }

    public GlobalProfileResponseBody setProfileSummary(GlobalProfileSummary profileSummary) {
        this.profileSummary = profileSummary;
        return this;
    }

    public GlobalProfileIdentityAge getIdentityAge() {
        return identityAge;
    }

    public GlobalProfileResponseBody setIdentityAge(GlobalProfileIdentityAge identityAge) {
        this.identityAge = identityAge;
        return this;
    }

    public GlobalProfileUserDecisions getUserDecisions() {
        return userDecisions;
    }

    public GlobalProfileResponseBody setUserDecisions(GlobalProfileUserDecisions userDecisions) {
        this.userDecisions = userDecisions;
        return this;
    }

    public GlobalProfileChargebacks getChargebacks() {
        return chargebacks;
    }

    public GlobalProfileResponseBody setChargebacks(GlobalProfileChargebacks chargebacks) {
        this.chargebacks = chargebacks;
        return this;
    }

    public GlobalProfileOrders getOrders() {
        return orders;
    }

    public GlobalProfileResponseBody setOrders(GlobalProfileOrders orders) {
        this.orders = orders;
        return this;
    }

    public GlobalProfileTransactions getTransactions() {
        return transactions;
    }

    public GlobalProfileResponseBody setTransactions(GlobalProfileTransactions transactions) {
        this.transactions = transactions;
        return this;
    }

    public GlobalProfileLocations getLocations() {
        return locations;
    }

    public GlobalProfileResponseBody setLocations(GlobalProfileLocations locations) {
        this.locations = locations;
        return this;
    }
}
