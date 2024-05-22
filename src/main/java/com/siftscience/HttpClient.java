package com.siftscience;

import java.io.IOException;

import com.siftscience.utils.OkHttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {

    private final OkHttpClient okClient;
    private boolean enqueueRequests;

    public HttpClient(OkHttpClient okHttpClient) {
        this.okClient = okHttpClient;
    }

    public OkHttpClient getOkClient() {
        return okClient;
    }

    public Response execute(Request request) throws IOException {
        return enqueueRequests ? OkHttpUtils.execute(request, okClient) :
            okClient.newCall(request).execute();
    }

    public void enqueueRequests() {
        this.enqueueRequests = true;
    }
}
