package com.joniaranguri.platzi.android.books.list.data.remote.api

import com.joniaranguri.platzi.android.books.list.data.remote.model.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {

    @GET(BOOKS_PATH)
    suspend fun getBookList(
        @Query("hours") hours: Int = QUERY_HOURS,
        @Query("minimum") minimum: Int = QUERY_MINIMUM,
        @Query("sort_by_count") sortByCount: Boolean = QUERY_SORT_BY_COUNT,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): BookListResponse

    companion object {
        const val BOOKS_PATH = "/trending/hours.json"
        const val QUERY_MINIMUM = 3
        const val QUERY_HOURS = 24
        const val QUERY_SORT_BY_COUNT = false

    }
}