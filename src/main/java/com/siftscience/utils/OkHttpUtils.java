package com.siftscience.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class OkHttpUtils {
    private OkHttpUtils() {

    }

    /**
     * There is a <a href="https://github.com/square/okhttp/issues/7841">bug</a> on okHttp library,
     * which causes java.net.SocketTimeoutException from HTTP/2 connection to leave dead okhttp
     * clients in pool.
     * This utility method is used as a
     * <a href="https://github.com/androidx/media/commit/80928e730c53729d147264a910fb327ba88be257">
     * workaround</a>
     * to enqueue and sync wait for response
     *
     * @param request  - request to send
     * @param okClient - client library
     * @return - response
     */
    public static Response execute(Request request, OkHttpClient okClient) throws IOException {
        CompletableFuture<Response> result = new CompletableFuture<>();
        okClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                result.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response httpResponse) {
                result.complete(httpResponse);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException e) {
            // Intentionally don't interrupt, as it causes okHHtp fail as described
            // in the link in javadoc
            // Thread.currentThread().interrupt();
            result.cancel(true);
            throw new InterruptedIOException("Interrupted while waiting for reply from Sift");
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }
}
