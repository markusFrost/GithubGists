package ru.markusfrost.okhttplibrary;

import android.content.Context;
import android.os.SystemClock;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockingInterceptor implements Interceptor {

    private final RequestsHandler mHandlers;

    private final Random mRandom;

    private MockingInterceptor(Context context, Map<String, String> responsesMap) {
        mHandlers = new RequestsHandler(context, responsesMap);
        mRandom = new SecureRandom();
    }

    @NonNull
    public static Interceptor create(Context context, Map<String, String> responsesMap) {
        return new MockingInterceptor(context, responsesMap);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();
        if (mHandlers.shouldIntercept(path)) {
            Response response = mHandlers.proceed(request, path);
            int stubDelay = 500 + mRandom.nextInt(2500);
            SystemClock.sleep(stubDelay);
            return response;
        }
        return chain.proceed(request);
    }
}

