package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.siftscience.model.GetDecisionFieldSet.*;

public class GetDecisionsResponseBody extends BaseResponseBody<GetDecisionsResponseBody>{
    @Expose @SerializedName("data") private final List<Decision> decisions;
    @Expose @SerializedName("has_more") private final Boolean hasMore;
    @Expose @SerializedName("total_results") private final Long totalResults;
    @Expose @SerializedName("next_ref") private final String nextRef;


    public GetDecisionsResponseBody(List<Decision> decisions, boolean hasMore, long totalResults, String nextRef) {
        this.decisions = decisions;
        this.hasMore = hasMore;
        this.totalResults = totalResults;
        this.nextRef = nextRef;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public Boolean isHasMore() {
        return hasMore;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public String getNextRef() {
        return nextRef;
    }

    public class Decision {
        @Expose @SerializedName("id") private final String id;
        @Expose @SerializedName("name") private final String name;
        @Expose @SerializedName("description") private final String description;
        @Expose @SerializedName("category") private final DecisionCategory category;
        @Expose @SerializedName("entity_type") private final EntityType entityType;
        @Expose @SerializedName("abuse_type") private final AbuseType abuseType;
        @Expose @SerializedName("webhook_url") private final String webhookUrl;
        @Expose @SerializedName("created_at") private final Long createdAt;
        @Expose @SerializedName("created_by") private final String createdBy;
        @Expose @SerializedName("updated_at") private final Long updatedAt;
        @Expose @SerializedName("updated_by") private final String updatedBy;

        public Decision(String id, String name, String description, DecisionCategory category,
                        EntityType entityType, AbuseType abuseType, String webhookUrl,
                        long createdAt, String createdBy, long updatedAt, String updatedBy) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.category = category;
            this.entityType = entityType;
            this.abuseType = abuseType;
            this.webhookUrl = webhookUrl;
            this.createdAt = createdAt;
            this.createdBy = createdBy;
            this.updatedAt = updatedAt;
            this.updatedBy = updatedBy;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public DecisionCategory getCategory() {
            return category;
        }

        public EntityType getEntityType() {
            return entityType;
        }

        public AbuseType getAbuseType() {
            return abuseType;
        }

        public String getWebhookUrl() {
            return webhookUrl;
        }

        public Long getCreatedAt() {
            return createdAt;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public Long getUpdatedAt() {
            return updatedAt;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }
    }
}
