package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ScoreResponse {
    @Expose @SerializedName("user_id") private String userId;
    @Expose @SerializedName("status") private Integer status;
    @Expose @SerializedName("error_message") private String errorMessage;
    @Expose @SerializedName("scores") private Map<String, AbuseScore> scores;
    @Expose @SerializedName("latest_labels") private Map<String, Label> latestLabels;
    @Expose @SerializedName("workflow_statuses") private List<WorkflowStatus> workflowStatuses;

    public String getUserId() {
        return userId;
    }

    public ScoreResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ScoreResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ScoreResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Map<String, AbuseScore> getScores() {
        return scores;
    }

    public ScoreResponse setScores(Map<String, AbuseScore> scores) {
        this.scores = scores;
        return this;
    }

    public Map<String, Label> getLatestLabels() {
        return latestLabels;
    }

    public ScoreResponse setLatestLabels(Map<String, Label> latestLabels) {
        this.latestLabels = latestLabels;
        return this;
    }

    public List<WorkflowStatus> getWorkflowStatuses() {
        return workflowStatuses;
    }

    public ScoreResponse setWorkflowStatuses(List<WorkflowStatus> workflowStatuses) {
        this.workflowStatuses = workflowStatuses;
        return this;
    }

    public WorkflowStatus getWorkflowStatus(int i) {
        return workflowStatuses.get(i);
    }
}
