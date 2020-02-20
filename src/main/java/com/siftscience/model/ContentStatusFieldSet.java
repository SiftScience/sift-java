package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentStatusFieldSet extends BaseAppBrowserSiteBrandFieldSet<ContentStatusFieldSet> {
    public static ContentStatusFieldSet fromJson(String json) {
        return gson.fromJson(json, ContentStatusFieldSet.class);
    }

    @Expose @SerializedName("$content_id") private String contentId;
    @Expose @SerializedName("$status") private String status;

    @Override
    public String getEventType() {
        return "$content_status";
    }

    public String getStatus() {
        return status;
    }

    public ContentStatusFieldSet setStatus(String status) {
        this.status = status;
        return this;
    }


    public String getContentId() {
        return contentId;
    }

    public ContentStatusFieldSet setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }
}
