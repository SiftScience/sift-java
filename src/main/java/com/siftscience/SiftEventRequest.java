package com.siftscience;

import com.siftscience.model.FieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

public class SiftEventRequest extends SiftRequest {

    private boolean returnScore;
    private List<String> abuseTypes;

    SiftEventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields) {
        super(baseUrl, okClient, fields);
        this.returnScore = false;
        abuseTypes = null;
    }

    SiftEventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields,
                            boolean returnScore) {
        super(baseUrl, okClient, fields);
        this.returnScore = returnScore;
        this.abuseTypes = null;
    }

    SiftEventRequest(HttpUrl baseUrl, OkHttpClient okClient, FieldSet fields,
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
