package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class EntityScoreResponseBody extends BaseResponseBody<EntityScoreResponseBody> {
    public static EntityScoreResponseBody fromJson(String json) {
        return gson.fromJson(json, EntityScoreResponseBody.class);
    }

    @Expose @SerializedName("entity_type") private String entityType;
    @Expose @SerializedName("entity_id") private String entityId;
    @Expose @SerializedName("scores") private Map<String, AbuseScoreV205> scores;
    @Expose @SerializedName("latest_labels") private Map<String, Label> latestLabels;
    @Expose @SerializedName("latest_decisions") private Map<String, Decision> latestDecisions;
    @Expose @SerializedName("workflow_statuses") private List<WorkflowStatus> workflowStatuses;

    public String getEntityType() {
        return entityType;
    }

    public EntityScoreResponseBody setEntityType(String entityType) {
        this.entityType = entityType;
        return this;
    }

    public String getEntityId() {
        return entityId;
    }

    public EntityScoreResponseBody setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public Map<String, AbuseScoreV205> getScores() {
        return scores;
    }

    public EntityScoreResponseBody setScores(Map<String, AbuseScoreV205> scores) {
        this.scores = scores;
        return this;
    }

    public Map<String, Label> getLatestLabels() {
        return latestLabels;
    }

    public EntityScoreResponseBody setLatestLabels(Map<String, Label> latestLabels) {
        this.latestLabels = latestLabels;
        return this;
    }

    public Map<String, Decision> getLatestDecisions() {
        return latestDecisions;
    }

    public EntityScoreResponseBody setLatestDecisions(Map<String, Decision> latestDecisions) {
        this.latestDecisions = latestDecisions;
        return this;
    }

    public List<WorkflowStatus> getWorkflowStatuses() {
        return workflowStatuses;
    }

    public EntityScoreResponseBody setWorkflowStatuses(List<WorkflowStatus> workflowStatuses) {
        this.workflowStatuses = workflowStatuses;
        return this;
    }

    public WorkflowStatus getWorkflowStatus(int i) {
        return workflowStatuses.get(i);
    }
}
