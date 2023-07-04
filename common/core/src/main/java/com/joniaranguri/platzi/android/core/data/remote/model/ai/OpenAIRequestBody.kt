package com.joniaranguri.platzi.android.core.data.remote.model.ai

import com.squareup.moshi.Json

data class OpenAIRequestBody(
    @Json(name = "prompt")
    val prompt: String,

    @Json(name = "model")
    val model: String = MODEL_DA_VINCI,

    @Json(name = "temperature")
    val temperature: Double = DEFAULT_TEMPERATURE,

    @Json(name = "max_tokens")
    val maxTokens: Int = DEFAULT_MAX_TOKENS,

    @Json(name = "top_p")
    val topP: Double = DEFAULT_TOP_P
) {
    companion object {
        const val MODEL_DA_VINCI = "text-davinci-003"
        const val DEFAULT_TEMPERATURE = 0.3
        const val DEFAULT_MAX_TOKENS = 500
        const val DEFAULT_TOP_P = 1.0
    }
}