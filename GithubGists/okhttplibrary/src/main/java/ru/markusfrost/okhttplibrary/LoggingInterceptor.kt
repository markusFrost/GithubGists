package ru.markusfrost.okhttplibrary

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.IOException

class LoggingInterceptor private constructor() : Interceptor {

    private val mLoggingInterceptor: Interceptor

    init {
        mLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return mLoggingInterceptor.intercept(chain)
    }

    companion object {

        fun create(): Interceptor {
            return LoggingInterceptor()
        }
    }

}
