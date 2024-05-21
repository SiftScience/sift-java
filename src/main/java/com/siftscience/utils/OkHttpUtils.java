package com.siftscience.utils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.siftscience.exception.SiftException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class OkHttpUtils {

    private static final int DEFAULT_TIMEOUT_SEC = 30;

    /**
     * There is a <a href="https://github.com/square/okhttp/issues/7841">bug</a> on okHttp library,
     * which causes java.net.SocketTimeoutException from HTTP/2 connection to leave dead okhttp
     * clients in pool.
     * This utility method is used as a
     * <a href="https://github.com/square/okhttp/issues/3146#issuecomment-1469679373">workaround</a>
     * to enqueue and sync wait for response
     *
     * @param request  - request to send
     * @param okClient - client library
     * @return - response
     */
    public static Response execute(Request request, OkHttpClient okClient) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference<Response> response = new AtomicReference<>();
        okClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                throw new SiftException(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response httpResponse) {
                response.set(httpResponse);
                countDownLatch.countDown();
            }
        });
        try {
            if (countDownLatch.await(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)) {
                return response.get();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SiftException("Interrupted while waiting for reply from Sift");
        }
        throw new SiftException("Timeout while waiting for reply from Sift");
    }
}
