package com.siftscience;

import com.siftscience.model.WorkflowStatus;
import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.*;

import java.io.IOException;

public class WorkflowStatusRequest extends SiftRequest<WorkflowStatusResponse> {
    WorkflowStatusRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("v3")
                .addPathSegment("accounts")
                .addPathSegment(((WorkflowStatusFieldSet)fieldSet).getAccountId())
                .addPathSegment("workflows")
                .addPathSegment("runs")
                .addPathSegment(((WorkflowStatusFieldSet)fieldSet).getWorkflowRunId())
                .build();
        return url;
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
