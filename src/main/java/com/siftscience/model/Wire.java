package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wire {

    @Expose @SerializedName("$wire_type") private String wireType;
    @Expose @SerializedName("$account_holder_name") private String accountHolderName;
    @Expose @SerializedName("$account_number") private String accountNumber;
    @Expose @SerializedName("$bank_name") private String bankName;
    @Expose @SerializedName("$bank_country") private String bankCountry;
    @Expose @SerializedName("$routing_number") private String routingNumber;
    @Expose @SerializedName("$swift_id") private String swiftId;
    @Expose @SerializedName("$bic") private String bic;

    public String getWireType() {
        return wireType;
    }

    public Wire setWireType(String wireType) {
        this.wireType = wireType;
        return this;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Wire setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Wire setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public Wire setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getBankCountry() {
        return bankCountry;
    }

    public Wire setBankCountry(String bankCountry) {
        this.bankCountry = bankCountry;
        return this;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public Wire setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
        return this;
    }

    public String getSwiftId() {
        return swiftId;
    }

    public Wire setSwiftId(String swiftId) {
        this.swiftId = swiftId;
        return this;
    }

    public String getBic() {
        return bic;
    }

    public Wire setBic(String bic) {
        this.bic = bic;
        return this;
    }
}