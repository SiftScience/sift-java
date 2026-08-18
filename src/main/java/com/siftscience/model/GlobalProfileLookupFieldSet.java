package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;
import com.siftscience.exception.MissingFieldException;

/**
 * Field set for {@code POST /v3/accounts/{accountId}/global_profile/lookup}. At least one of
 * {@code email} or {@code phone} is required.
 */
public class GlobalProfileLookupFieldSet extends FieldSet<GlobalProfileLookupFieldSet> {
    @Expose @SerializedName("email") private String email;
    @Expose @SerializedName("phone") private String phone;

    public GlobalProfileLookupFieldSet() {}

    public static GlobalProfileLookupFieldSet fromJson(String json) {
        return gson.fromJson(json, GlobalProfileLookupFieldSet.class);
    }

    public String getEmail() {
        return email;
    }

    public GlobalProfileLookupFieldSet setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public GlobalProfileLookupFieldSet setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
        if ((email == null || email.isEmpty()) && (phone == null || phone.isEmpty())) {
            throw new MissingFieldException(
                    "At least one of 'email' or 'phone' is required for a global profile lookup.");
        }
    }
}
