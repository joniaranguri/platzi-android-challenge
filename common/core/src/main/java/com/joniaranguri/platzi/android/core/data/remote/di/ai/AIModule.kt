package com.joniaranguri.platzi.android.core.data.remote.di.ai


import com.joniaranguri.platzi.android.core.data.remote.api.OpenAIApi
import com.joniaranguri.platzi.android.core.network.createRetrofitWithMoshi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AIModule {

    @Provides
    @Singleton
    fun provideOpenAPI(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): OpenAIApi {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = OPEN_API_URL
        )
    }

    companion object {
        const val OPEN_API_URL = "https://api.openai.com"
    }
}