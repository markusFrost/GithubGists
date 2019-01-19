package ru.markusfrost.okhttplibrary

import android.content.Context
import android.os.SystemClock
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.SecureRandom
import java.util.*

class MockingInterceptor private constructor(context: Context, responsesMap: Map<String, String>) : Interceptor {

    private val mHandlers: RequestsHandler = RequestsHandler(context, responsesMap)
    private val mRandom: Random = SecureRandom()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        if (mHandlers.shouldIntercept(path)) {
            val response = mHandlers.proceed(request, path)
            val stubDelay = 500 + mRandom.nextInt(2500)
            SystemClock.sleep(stubDelay.toLong())
            return response
        }
        return chain.proceed(request)
    }

    companion object {

        fun create(context: Context, responsesMap: Map<String, String>): Interceptor {
            return MockingInterceptor(context, responsesMap)
        }
    }
}

