package com.joniaranguri.platzi.android.books.list.data.remote.di

import com.joniaranguri.platzi.android.books.list.data.remote.api.BooksApi
import com.joniaranguri.platzi.android.core.data.remote.di.base.RemoteModule.Companion.BASE_URL
import com.joniaranguri.platzi.android.core.network.createRetrofitWithMoshi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
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

}