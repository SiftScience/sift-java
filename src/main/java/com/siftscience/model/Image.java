package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @Expose @SerializedName("$md5_hash") private String md5Hash;
    @Expose @SerializedName("$link") private String link;
    @Expose @SerializedName("$description") private String description;

    public String getMd5Hash() {
        return md5Hash;
    }

    public Image setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Image setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Image setDescription(String description) {
        this.description = description;
        return this;
    }
}