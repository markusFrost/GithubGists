package ru.markusfrost.okhttplibrary

import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.io.InputStream

object OkHttpResponse {

    private val EMPTY_BODY = ByteArray(0)

    private val APPLICATION_JSON = MediaType.parse("application/json")

    @Throws(IOException::class)
    fun success(request: Request, stream: InputStream): Response {
        val buffer = Buffer().readFrom(stream)
        return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(APPLICATION_JSON, buffer.size(), buffer))
                .build()
    }

    fun error(request: Request, code: Int, message: String): Response {
        return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message(message)
                .body(ResponseBody.create(APPLICATION_JSON, EMPTY_BODY))
                .build()
    }

}
