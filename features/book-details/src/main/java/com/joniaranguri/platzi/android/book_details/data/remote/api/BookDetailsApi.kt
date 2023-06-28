package com.joniaranguri.platzi.android.book_details.data.remote.api

import com.joniaranguri.platzi.android.book_details.data.remote.model.AuthorRawResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookAnalyticsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookDetailsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookRatingsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailsApi {

    @GET(BOOK_DETAILS_PATH)
    suspend fun getBookDetails(
        @Path(PARAM_ID) bookId: String
    ): BookDetailsResponse

    @GET(AUTHORS_DETAILS_PATH)
    suspend fun getAuthorsDetails(
        @Path(PARAM_ID) bookId: String
    ): AuthorRawResponse


    @GET(BOOK_RATINGS_PATH)
    suspend fun getBookRatings(
        @Path(PARAM_ID) bookId: String
    ): BookRatingsResponse


    @GET(BOOK_ANALYTICS_PATH)
    suspend fun getBookAnalytics(
        @Path(PARAM_ID) bookId: String
    ): BookAnalyticsResponse


    companion object {
        private const val PARAM_ID = "id"
        const val BOOK_DETAILS_PATH = "/works/{${PARAM_ID}}.json"
        const val AUTHORS_DETAILS_PATH = "/authors/{${PARAM_ID}}.json"
        const val BOOK_RATINGS_PATH = "/works/{${PARAM_ID}}/ratings.json"
        const val BOOK_ANALYTICS_PATH = "/works/{${PARAM_ID}}/bookshelves.json"
    }
}