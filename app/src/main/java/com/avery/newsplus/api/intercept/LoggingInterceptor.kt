package com.avery.newsplus.api.intercept

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.String

/**
 * @author AveryZhong
 */

class LoggingInterceptor : Interceptor {
    companion object {
        private const val TAG = "LoggingInterceptor"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Log.i(
            TAG,
            String.format(
                "Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()
            )
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.i(
            TAG,
            String.format(
                "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()
            )
        )
        return response
    }
}