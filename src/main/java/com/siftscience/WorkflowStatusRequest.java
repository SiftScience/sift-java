package com.siftscience;

import com.siftscience.model.WorkflowStatus;
import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.*;

import java.io.IOException;

public class WorkflowStatusRequest extends SiftRequest<WorkflowStatusResponse> {
    WorkflowStatusRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
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
