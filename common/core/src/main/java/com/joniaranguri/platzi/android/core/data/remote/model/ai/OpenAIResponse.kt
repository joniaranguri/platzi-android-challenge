package com.joniaranguri.platzi.android.core.data.remote.model.ai

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenAIResponse(
    @Json(name = "choices")
    val choices: List<OpenAIText>
)