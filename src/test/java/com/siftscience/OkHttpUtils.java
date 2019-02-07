package com.siftscience;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.mockwebserver.MockWebServer;

public final class OkHttpUtils {

    private OkHttpUtils() {

    }

    public static Interceptor urlRewritingInterceptor(MockWebServer mockWebServer) {
        return chain -> chain.proceed(
            chain.request().newBuilder()
                .url(mockWebServer.url(httpUrlPathAndQuery(chain.request().url())))
                .build());
    }

    private static String httpUrlPathAndQuery(HttpUrl url) {
        if (url.query() == null) {
            return url.encodedPath();
        } else {
            return url.encodedPath() + "?" + url.query();
        }
    }
}
