package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sepa {

    @Expose @SerializedName("$sepa_type") private String sepaType;
    @Expose @SerializedName("$account_holder_name") private String accountHolderName;
    @Expose @SerializedName("$shortened_iban") private String shortenedIban;
    @Expose @SerializedName("$bic") private String bic;
    @Expose @SerializedName("$mandate_id") private String mandateId;

    public String getSepaType() {
        return sepaType;
    }

    public Sepa setSepaType(String sepaType) {
        this.sepaType = sepaType;
        return this;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Sepa setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public String getShortenedIban() {
        return shortenedIban;
    }

    public Sepa setShortenedIban(String iban) {
        this.shortenedIban = iban;
        return this;
    }

    public String getBic() {
        return bic;
    }

    public Sepa setBic(String bic) {
        this.bic = bic;
        return this;
    }

    public String getMandateId() {
        return mandateId;
    }

    public Sepa setMandateId(String mandateId) {
        this.mandateId = mandateId;
        return this;
    }
}
