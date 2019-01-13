package ru.white_monk_team.okhttplibrary;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import okhttp3.Request;
import okhttp3.Response;

public class RequestsHandler {

    private Map<String, String> mResponsesMap;
    private Context mContext;

    public RequestsHandler(Context context, Map<String, String> responsesMap) {
        mResponsesMap = responsesMap;
        mContext = context;
    }

    public boolean shouldIntercept(@NonNull String path) {
        Set<String> keys = mResponsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public Response proceed(@NonNull Request request, @NonNull String path) {
        Set<String> keys = mResponsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                String mockResponsePath = mResponsesMap.get(interceptUrl);
                return createResponseFromAssets(request, mockResponsePath);
            }
        }

        return OkHttpResponse.error(request, 500, "Incorrectly intercepted request");
    }

    @NonNull
    private Response createResponseFromAssets(@NonNull Request request, @NonNull String assetPath) {
        try {
            final InputStream stream = mContext.getAssets().open(assetPath);
            try {
                return OkHttpResponse.success(request, stream);
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            return OkHttpResponse.error(request, 500, e.getMessage());
        }
    }
}
