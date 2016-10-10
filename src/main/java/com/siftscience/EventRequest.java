package com.siftscience;

import com.sun.istack.internal.NotNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class EventRequest extends SiftRequest<EventResponse> {

    private boolean returnScore;
    private List<String> abuseTypes;

    EventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
        this.returnScore = false;
        abuseTypes = null;
    }

    @Override
    EventResponse buildResponse(@NotNull Response response, FieldSet requestFields)
            throws IOException {
        return new EventResponse(response, requestFields);
    }

    EventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields,
                 boolean returnScore) {
        super(baseUrl, okClient, fields);
        this.returnScore = returnScore;
        this.abuseTypes = null;
    }

    EventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields,
                 boolean returnScore, List<String> abuseTypes) {
        super(baseUrl, okClient, fields);
        this.returnScore = returnScore;
        this.abuseTypes = abuseTypes;
    }

    @Override
    protected HttpUrl path(HttpUrl baseUrl) {
        HttpUrl.Builder builder = baseUrl.newBuilder()
                .addPathSegment("events");
        if (returnScore) {
            builder.addQueryParameter("return_score", "true");
        }
        if (abuseTypes != null && abuseTypes.size() > 0) {
            String queryParamVal = "";
            for (String abuseType : abuseTypes) {
                queryParamVal += (abuseType + ",");
            }
            builder.addQueryParameter("abuse_types",
                    queryParamVal.substring(0, queryParamVal.length() - 1));
        }
        return builder.build();
    }
}
