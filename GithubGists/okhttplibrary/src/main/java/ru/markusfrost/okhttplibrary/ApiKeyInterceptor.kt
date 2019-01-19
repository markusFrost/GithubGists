package ru.markusfrost.okhttplibrary

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor private constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }

    companion object {

        fun create(): Interceptor {
            return ApiKeyInterceptor()
        }
    }
}
