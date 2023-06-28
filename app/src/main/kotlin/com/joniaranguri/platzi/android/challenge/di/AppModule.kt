package com.joniaranguri.platzi.android.challenge.di

import com.joniaranguri.platzi.android.challenge.app.PlatziAndroidChallengeApp
import com.joniaranguri.platzi.android.challenge.app.PlatziChallengeNetworkConfig
import com.joniaranguri.platzi.android.core.base.app.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(): PlatziAndroidChallengeApp {
        return PlatziAndroidChallengeApp()
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return PlatziChallengeNetworkConfig()
    }

}