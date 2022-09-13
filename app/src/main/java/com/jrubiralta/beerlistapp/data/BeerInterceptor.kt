package com.jrubiralta.beerlistapp.data

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class BeerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(originalHttpUrl)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}