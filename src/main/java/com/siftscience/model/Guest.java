package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guest {
    @Expose @SerializedName("$name") private String name;
    @Expose @SerializedName("$email") private String email;
    @Expose @SerializedName("$phone") private String phone;
    @Expose @SerializedName("$loyalty_program") private String loyaltyProgram;
    @Expose @SerializedName("$loyalty_program_id") private String loyaltyProgramId;
    @Expose @SerializedName("$birth_date") private String birthDate;

    public String getName() {
        return name;
    }

    public Guest setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Guest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Guest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public Guest setLoyaltyProgram(String loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
        return this;
    }

    public String getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public Guest setLoyaltyProgramId(String loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Guest setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
