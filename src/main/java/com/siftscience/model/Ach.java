package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ach {
    @Expose @SerializedName("$ach_type") private String achType;
    @Expose @SerializedName("$routing_number") private String routingNumber;
    @Expose @SerializedName("$account_number") private String accountNumber;
    @Expose @SerializedName("$account_holder_name") private String accountHolderName;

    public String getAchType() {
        return achType;
    }

    public Ach setAchType(String achType) {
        this.achType = achType;
        return this;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public Ach setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Ach setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Ach setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

}
