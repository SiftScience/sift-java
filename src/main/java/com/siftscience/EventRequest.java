package com.siftscience;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * EventRequest is the request type for the Sift Events API.
 * https://siftscience.com/developers/docs/curl/events-api
 */
public class EventRequest extends SiftRequest<EventResponse> {
    // The abuse types to return synchronous scores for.
    private List<String> abuseTypes;
    private boolean isWorkflowStatus = false;
    private boolean forceWorkflowRun = false;
    private boolean isReturnRouteInfo = false;

    EventRequest(HttpUrl baseUrl, String accountId, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, accountId, okClient, fields);
        abuseTypes = null;
    }

    @Override
    EventResponse buildResponse(Response response, FieldSet requestFields)
            throws IOException {
        return new EventResponse(response, requestFields);
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder builder = baseUrl.newBuilder()
                .addPathSegment("v205").addPathSegment("events");

        if (isWorkflowStatus) {
            builder.addQueryParameter("return_workflow_status", "true");
        } else if (abuseTypes != null) {
            builder.addQueryParameter("return_score", "true");
        }
        if (isReturnRouteInfo) {
            builder.addQueryParameter("return_route_info", "true");
        }
        if (forceWorkflowRun) {
            builder.addQueryParameter("force_workflow_run", "true"); 
        }

        // returnScore and abuseTypes are encoded into the URL as query params rather than JSON.
        if (abuseTypes != null && abuseTypes.size() > 0) {
            builder.addQueryParameter("abuse_types", StringUtils.joinWithComma(abuseTypes));
        }
        return builder.build();
    }

    public EventRequest withScores(String... abuseTypes) {
        this.abuseTypes = Arrays.asList(abuseTypes);
        return this;
    }

    public EventRequest withWorkflowStatus() {
        this.isWorkflowStatus = true;
        return this;
    }

    public EventRequest withForceWorkflowRun() {
        this.forceWorkflowRun = true;
        return this;
    }

    public EventRequest withRouteInfo() {
        this.isReturnRouteInfo = true;
        return this;
    }
}
