package com.joniaranguri.platzi.android.books.list.data.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.joniaranguri.platzi.android.books.list.data.remote.api.BooksApi
import com.joniaranguri.platzi.android.core.base.app.NetworkConfig
import com.joniaranguri.platzi.android.core.network.createChuckInterceptor
import com.joniaranguri.platzi.android.core.network.createHttpLoggingInterceptor
import com.joniaranguri.platzi.android.core.network.createHttpRequestInterceptor
import com.joniaranguri.platzi.android.core.network.createMoshi
import com.joniaranguri.platzi.android.core.network.createOkHttpClient
import com.joniaranguri.platzi.android.core.network.createRetrofitWithMoshi
import com.joniaranguri.platzi.android.core.network.interceptor.HttpRequestInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = networkConfig.isDev())
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(): HttpRequestInterceptor {
        return createHttpRequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                chuckerInterceptor,
                httpRequestInterceptor
            ),
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideBooksApi(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): BooksApi {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
    }

    companion object {
        private const val BASE_URL = "base_url"

    }
}