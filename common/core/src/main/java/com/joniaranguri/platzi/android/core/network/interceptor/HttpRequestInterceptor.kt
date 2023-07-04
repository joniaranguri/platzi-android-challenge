package com.joniaranguri.platzi.android.core.network.interceptor

import com.joniaranguri.platzi.android.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .url(originalRequest.url)
            .addHeader("Authorization", "Bearer ${BuildConfig.AI_TOKEN}")
            .build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}