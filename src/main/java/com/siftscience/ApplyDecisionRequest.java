package com.siftscience;

import java.io.IOException;

import com.siftscience.model.ApplyDecisionFieldSet;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class ApplyDecisionRequest extends SiftRequest<ApplyDecisionResponse>{

    ApplyDecisionRequest(HttpUrl baseUrl, String accountId, HttpClient httpClient, ApplyDecisionFieldSet fields) {
        super(baseUrl, accountId, httpClient, fields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder path = baseUrl.newBuilder("v3/accounts")
                .addPathSegment(getAccountId())
                .addPathSegment("users")
                .addPathSegment(((ApplyDecisionFieldSet) fieldSet).getUserId());

        String orderId = ((ApplyDecisionFieldSet) fieldSet).getOrderId();
        String sessionId = ((ApplyDecisionFieldSet) fieldSet).getSessionId();
        String contentId = ((ApplyDecisionFieldSet) fieldSet).getContentId();

        if (orderId != null && !orderId.isEmpty()) {
            path.addPathSegment("orders").addPathSegment(orderId);
        } else if (sessionId != null && !sessionId.isEmpty()) {
            path.addPathSegment("sessions").addPathSegment(sessionId);
        } else if (contentId != null && !contentId.isEmpty()) {
            path.addPathSegment("content").addPathSegment(contentId);
        }

        return path.addPathSegment("decisions").build();
    }

    @Override
    ApplyDecisionResponse buildResponse(okhttp3.Response response, FieldSet requestFields) throws IOException {
        return new ApplyDecisionResponse(response, requestFields);
    }

    @Override
    protected void modifyRequestBuilder(Request.Builder builder) {
        super.modifyRequestBuilder(builder);
        builder.header("Authorization", Credentials.basic(fieldSet.getApiKey(),""));
    }

}
