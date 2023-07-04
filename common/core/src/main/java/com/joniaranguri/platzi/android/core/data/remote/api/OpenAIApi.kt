package com.joniaranguri.platzi.android.core.data.remote.api

import com.joniaranguri.platzi.android.core.data.remote.model.ai.OpenAIRequestBody
import com.joniaranguri.platzi.android.core.data.remote.model.ai.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIApi {

    @POST(COMPLETIONS_PATH)
    suspend fun getOpenAIResult(
        @Body openAIRequestBody: OpenAIRequestBody
    ): OpenAIResponse

    companion object {
        const val COMPLETIONS_PATH = "/v1/completions"
    }
}
