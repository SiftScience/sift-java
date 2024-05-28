package com.siftscience;

import java.io.IOException;

import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class WorkflowStatusRequest extends SiftRequest<WorkflowStatusResponse> {
    WorkflowStatusRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, FieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        return baseUrl.newBuilder()
                .addPathSegment("v3")
                .addPathSegment("accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("workflows")
                .addPathSegment("runs")
                .addPathSegment(((WorkflowStatusFieldSet)fieldSet).getWorkflowRunId())
                .build();
    }

    @Override
    WorkflowStatusResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new WorkflowStatusResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
