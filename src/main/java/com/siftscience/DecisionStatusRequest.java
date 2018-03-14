package com.siftscience;

import com.siftscience.model.DecisionStatusFieldSet;
import com.sun.javafx.fxml.builder.URLBuilder;
import okhttp3.*;

import java.io.IOException;

import static com.siftscience.model.DecisionStatusFieldSet.ENTITY_CONTENT;

public class DecisionStatusRequest extends SiftRequest<DecisionStatusResponse> {
    DecisionStatusRequest(HttpUrl baseUrl, OkHttpClient okClient, DecisionStatusFieldSet fields) {
        super(baseUrl, okClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder builder = baseUrl.newBuilder()
            .addPathSegment("v3")
            .addPathSegment("accounts")
            .addPathSegment(((DecisionStatusFieldSet)fieldSet).getAccountId());
        if (((DecisionStatusFieldSet)fieldSet).getEntity().equals(ENTITY_CONTENT)) {
            return builder
                .addPathSegment("users")
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getUserId())
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntity())
                .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntityId())
                .addPathSegment("decisions")
                .build();
        }
        return builder
            .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntity())
            .addPathSegment(((DecisionStatusFieldSet)fieldSet).getEntityId())
            .addPathSegment("decisions")
            .build();
    }

    @Override
    DecisionStatusResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new DecisionStatusResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(), "")).get();
    }
}
