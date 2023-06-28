package com.joniaranguri.platzi.android.book_details.data.repository

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.book_details.data.local.dao.BookDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.BookDetailsApi
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookAnalyticsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookRatingsResponse
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails
import com.joniaranguri.platzi.android.book_details.domain.repository.BookDetailsRepository
import javax.inject.Inject


class BookDetailsRepositoryImpl @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val bookDetailsApi: BookDetailsApi,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val bookDetailsDao: BookDetailsDao
) : BookDetailsRepository {
    override suspend fun getBookDetails(
        bookId: String
    ): BookDetails {
        return bookDetailsDao.getBookDetails(bookId)?.toDomainModel()
            ?: bookDetailsApi.getBookDetails(
                bookId
            ).toDomainModel(bookId)
    }

    override suspend fun getBookRatings(bookId: String): BookRatingsResponse =
        bookDetailsApi.getBookRatings(bookId)

    override suspend fun getBookAnalytics(bookId: String): BookAnalyticsResponse =
        bookDetailsApi.getBookAnalytics(bookId)

    override suspend fun saveBookDetails(bookDetailsCached: BookDetailsCached) {
        bookDetailsDao.insert(bookDetailsCached)
    }

}
