package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateProfileFieldSet extends CreateContentFieldSet<CreateProfileFieldSet> {
    public static CreateProfileFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateProfileFieldSet.class);
    }

    @Expose @SerializedName("$profile") private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public CreateProfileFieldSet setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
}
