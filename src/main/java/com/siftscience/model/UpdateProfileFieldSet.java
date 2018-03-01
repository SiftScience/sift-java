package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileFieldSet extends UpdateContentFieldSet<UpdateProfileFieldSet> {
    public static UpdateProfileFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateProfileFieldSet.class);
    }

    @Expose @SerializedName("$profile") private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public UpdateProfileFieldSet setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
}
