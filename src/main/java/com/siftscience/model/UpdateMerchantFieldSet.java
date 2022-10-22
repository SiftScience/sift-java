package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class UpdateMerchantFieldSet extends FieldSet<UpdateMerchantFieldSet> {
    public static UpdateMerchantFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateMerchantFieldSet.class);
    }

    @Expose @SerializedName("id") private String id = null;

    @Expose @SerializedName("name") private String name = null;

    @Expose @SerializedName("description") private String description = null;

    @Expose @SerializedName("address") private MerchantAddress address = null;

    @Expose @SerializedName("category") private String category = null;

    @Expose @SerializedName("service_level") private String serviceLevel = null;

    @Expose @SerializedName("status") private String status = null;

    @Expose @SerializedName("risk_profile") private RiskProfile riskProfile = null;

    @Expose @SerializedName("merchant_Id") private String merchantId = null;

    public UpdateMerchantFieldSet setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public UpdateMerchantFieldSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateMerchantFieldSet setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateMerchantFieldSet setAddress(MerchantAddress address) {
        this.address = address;
        return this;
    }

    public MerchantAddress getAddress() {
        return address;
    }

    public UpdateMerchantFieldSet setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateMerchantFieldSet setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
        return this;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public String getStatus() {
        return status;
    }

    public UpdateMerchantFieldSet setStatus(MerchantStatusEnum status) {
        this.status = status.value;
        return this;
    }

    public UpdateMerchantFieldSet setRiskProfile(RiskProfile riskProfile) {
        this.riskProfile = riskProfile;
        return this;
    }

    public RiskProfile getRiskProfile() {
        return riskProfile;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public UpdateMerchantFieldSet setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }
}
