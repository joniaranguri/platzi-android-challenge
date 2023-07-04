package com.joniaranguri.platzi.android.core.data.remote.model.ai

import com.squareup.moshi.Json

data class OpenAIText(
    @Json(name = "text")
    val value: String
)
