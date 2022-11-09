package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class CreateMerchantFieldSet extends FieldSet<CreateMerchantFieldSet> {
    public static CreateMerchantFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateMerchantFieldSet.class);
    }

    @Expose @SerializedName("id") private String id = null;

    @Expose @SerializedName("name") private String name = null;

    @Expose @SerializedName("description") private String description = null;

    @Expose @SerializedName("address") private MerchantAddress address = null;

    @Expose @SerializedName("category") private String category = null;

    @Expose @SerializedName("service_level") private String serviceLevel = null;

    @Expose @SerializedName("status") private String status = null;

    @Expose @SerializedName("risk_profile") private RiskProfile riskProfile = null;

    public CreateMerchantFieldSet setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public CreateMerchantFieldSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateMerchantFieldSet setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateMerchantFieldSet setAddress(MerchantAddress address) {
        this.address = address;
        return this;
    }

    public MerchantAddress getAddress() {
        return address;
    }

    public CreateMerchantFieldSet setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CreateMerchantFieldSet setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
        return this;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public String getStatus() {
        return status;
    }

    public CreateMerchantFieldSet setStatus(MerchantStatusEnum status) {
        this.status = status.value;
        return this;
    }

    public CreateMerchantFieldSet setRiskProfile(RiskProfile riskProfile) {
        this.riskProfile = riskProfile;
        return this;
    }

    public RiskProfile getRiskProfile() {
        return riskProfile;
    }
}
