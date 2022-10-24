package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMerchantResponseBody extends MerchantBaseResponseBody<CreateMerchantResponseBody>{


    @Expose @SerializedName("id") private String id = null;

    @Expose @SerializedName("name") private String name = null;

    @Expose @SerializedName("created_at") private Long createdAt = null;

    @Expose @SerializedName("created_by") private String createdBy = null;

    @Expose @SerializedName("last_updated_at") private Long lastUpdatedAt = null;

    @Expose @SerializedName("last_updated_by") private String lastUpdatedBy = null;

    @Expose @SerializedName("description") private String description = null;

    @Expose @SerializedName("address") private MerchantAddress address = null;

    @Expose @SerializedName("category") private String category = null;

    @Expose @SerializedName("service_level") private String serviceLevel = null;

    @Expose @SerializedName("status") private String status = null;

    @Expose @SerializedName("risk_profile") private RiskProfile riskProfile = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MerchantAddress getAddress() {
        return address;
    }

    public void setAddress(MerchantAddress address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RiskProfile getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(RiskProfile riskProfile) {
        this.riskProfile = riskProfile;
    }

    public static CreateMerchantResponseBody fromJson(String jsonBody) {
        return gson.fromJson(jsonBody, CreateMerchantResponseBody.class);
    }
}
