package com.joniaranguri.platzi.android.book_details.domain.repository

import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookAnalyticsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookRatingsResponse
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails

interface BookDetailsRepository {
    suspend fun getBookDetails(bookId: String, bookTitle: String): BookDetails
    suspend fun getIABookDescription(bookTitle: String): String
    suspend fun getBookRatings(bookId: String): BookRatingsResponse
    suspend fun getBookAnalytics(bookId: String): BookAnalyticsResponse
    suspend fun saveBookDetails(bookDetailsCached: BookDetailsCached)
}