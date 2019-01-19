package ru.markusfrost.okhttplibrary

import android.content.Context
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RequestsHandler(private val mContext: Context, private val mResponsesMap: Map<String, String>) {

    fun shouldIntercept(path: String): Boolean {
        val keys = mResponsesMap.keys
        for (interceptUrl in keys) {
            if (path.contains(interceptUrl)) {
                return true
            }
        }
        return false
    }

    fun proceed(request: Request, path: String): Response {
        for (interceptUrl in mResponsesMap.keys) {
            if (path.contains(interceptUrl)) {
                return createResponseFromAssets(request,  mResponsesMap[interceptUrl]!!)
            }
        }

        return OkHttpResponse.error(request, 500, "Incorrectly intercepted request")
    }

    private fun createResponseFromAssets(request: Request, assetPath: String): Response {
        try {
            mContext.assets.open(assetPath).use { stream ->
                return OkHttpResponse.success(request, stream)
            }
        } catch (e: IOException) {
            return OkHttpResponse.error(request, 500, e.message!!)
        }

    }
}
