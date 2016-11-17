package com.siftscience;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siftscience.model.WorkflowStatus;
import okhttp3.Response;

import java.io.IOException;

public class WorkflowStatusResponse extends SiftResponse<WorkflowStatus> {
    WorkflowStatusResponse(Response okResponse, FieldSet requestBody) throws IOException {
        super(okResponse, requestBody);
    }

    @Override
    public void populateBodyFromJson(String jsonBody) {
        body = WorkflowStatus.fromJson(jsonBody);
    }
}
