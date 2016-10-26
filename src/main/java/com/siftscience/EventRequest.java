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

    EventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
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
                .addPathSegment("v204").addPathSegment("events");

        boolean hasAbuseTypes = abuseTypes != null && abuseTypes.size() > 0;
        if (isWorkflowStatus) {
            builder.addQueryParameter("return_workflow_status", "true");
        } else if (hasAbuseTypes) {
            builder.addQueryParameter("return_score", "true");
        }

        // returnScore and abuseTypes are encoded into the URL as query params rather than JSON.
        if (hasAbuseTypes) {
            String queryParamVal = "";
            for (String abuseType : abuseTypes) {
                queryParamVal += (abuseType + ",");
            }
            builder.addQueryParameter("abuse_types",
                    queryParamVal.substring(0, queryParamVal.length() - 1));
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
}
