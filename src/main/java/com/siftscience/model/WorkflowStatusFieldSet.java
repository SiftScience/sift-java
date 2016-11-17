package com.siftscience.model;

import com.siftscience.FieldSet;

public class WorkflowStatusFieldSet extends FieldSet<WorkflowStatusFieldSet> {
    private String accountId;
    private String workflowRunId;

    public String getAccountId() {
        return accountId;
    }

    public WorkflowStatusFieldSet setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getWorkflowRunId() {
        return workflowRunId;
    }

    public WorkflowStatusFieldSet setWorkflowRunId(String workflowRunId) {
        this.workflowRunId = workflowRunId;
        return this;
    }
}
