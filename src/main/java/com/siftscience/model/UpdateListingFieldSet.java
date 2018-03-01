package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateListingFieldSet extends UpdateContentFieldSet<UpdateListingFieldSet> {
    public static UpdateListingFieldSet fromJson(String json) {
        return gson.fromJson(json, UpdateListingFieldSet.class);
    }

    @Expose @SerializedName("$listing") private Listing listing;

    public Listing getListing() {
        return listing;
    }

    public UpdateListingFieldSet setListing(Listing listing) {
        this.listing = listing;
        return this;
    }
}
