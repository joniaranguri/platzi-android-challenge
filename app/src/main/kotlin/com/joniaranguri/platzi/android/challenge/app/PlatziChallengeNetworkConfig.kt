package com.joniaranguri.platzi.android.challenge.app

import com.joniaranguri.platzi.android.challenge.BuildConfig
import com.joniaranguri.platzi.android.core.base.app.NetworkConfig

class PlatziChallengeNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}