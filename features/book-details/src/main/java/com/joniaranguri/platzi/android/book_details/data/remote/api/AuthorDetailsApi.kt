package com.joniaranguri.platzi.android.book_details.data.remote.api

import com.joniaranguri.platzi.android.book_details.data.remote.model.AuthorDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorDetailsApi {

    @GET(AUTHORS_DETAILS_PATH)
    suspend fun getAuthorDetails(
        @Path(PARAM_ID) authorId: String
    ): AuthorDetailResponse

    companion object {
        private const val PARAM_ID = "id"
        const val AUTHORS_DETAILS_PATH = "/authors/{${PARAM_ID}}.json"
    }
}