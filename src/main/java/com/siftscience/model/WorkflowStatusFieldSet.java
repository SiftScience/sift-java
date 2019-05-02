package com.siftscience.model;

import com.siftscience.FieldSet;

public class WorkflowStatusFieldSet extends FieldSet<WorkflowStatusFieldSet> {
    private String workflowRunId;

    public String getWorkflowRunId() {
        return workflowRunId;
    }

    public WorkflowStatusFieldSet setWorkflowRunId(String workflowRunId) {
        this.workflowRunId = workflowRunId;
        return this;
    }
}
