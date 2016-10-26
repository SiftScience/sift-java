package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkflowStatusHistoryConfig {
    @Expose @SerializedName("decision_id") private String decisionId;
    @Expose @SerializedName("buttons") private List<WorkflowStatusHistoryConfigButton> buttons;

    public String getDecisionId() {
        return decisionId;
    }

    public WorkflowStatusHistoryConfig setDecisionId(String decisionId) {
        this.decisionId = decisionId;
        return this;
    }

    public List<WorkflowStatusHistoryConfigButton> getButtons() {
        return buttons;
    }

    public WorkflowStatusHistoryConfig setButtons(List<WorkflowStatusHistoryConfigButton> buttons) {
        this.buttons = buttons;
        return this;
    }
}
