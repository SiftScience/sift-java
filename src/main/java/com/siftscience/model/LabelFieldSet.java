package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.FieldSet;

public class LabelFieldSet extends FieldSet<LabelFieldSet> {
    public static LabelFieldSet fromJson(String json) {
        return gson.fromJson(json, LabelFieldSet.class);
    }

    private String userId;
    @Expose @SerializedName(IS_BAD) private Boolean isBad;
    @Expose @SerializedName(ABUSE_TYPE) private String abuseType;
    @Expose @SerializedName(TIME) private Long time;
    @Expose @SerializedName("$description") private String description;
    @Expose @SerializedName("$source") private String source;
    @Expose @SerializedName("$analyst") private String analyst;

    @Override
    protected boolean shouldJsonSerializeApiKey() {
        return true;
    }

    public Boolean getIsBad() {
        return isBad;
    }

    public LabelFieldSet setIsBad(Boolean isBad) {
        this.isBad = isBad;
        return this;
    }

    public String getAbuseType() {
        return abuseType;
    }

    public LabelFieldSet setAbuseType(String abuseType) {
        this.abuseType = abuseType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LabelFieldSet setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSource() {
        return source;
    }

    public LabelFieldSet setSource(String source) {
        this.source = source;
        return this;
    }

    public String getAnalyst() {
        return analyst;
    }

    public LabelFieldSet setAnalyst(String analyst) {
        this.analyst = analyst;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LabelFieldSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public LabelFieldSet setTime(Long time) {
        this.time = time;
        return this;
    }
}
