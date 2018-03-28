package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateListingFieldSet extends CreateContentFieldSet<CreateListingFieldSet> {
    public static CreateListingFieldSet fromJson(String json) {
        return gson.fromJson(json, CreateListingFieldSet.class);
    }

    @Expose @SerializedName("$listing") private Listing listing;

    public Listing getListing() {
        return listing;
    }

    public CreateListingFieldSet setListing(Listing listing) {
        this.listing = listing;
        return this;
    }
}
