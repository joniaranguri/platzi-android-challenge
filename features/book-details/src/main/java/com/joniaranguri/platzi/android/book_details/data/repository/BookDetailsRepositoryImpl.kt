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
import com.joniaranguri.platzi.android.core.data.remote.api.OpenAIApi
import com.joniaranguri.platzi.android.core.data.remote.model.ai.OpenAIRequestBody
import com.joniaranguri.platzi.android.core.data.remote.model.base.InconsistentType
import javax.inject.Inject


class BookDetailsRepositoryImpl @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val bookDetailsApi: BookDetailsApi,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val bookDetailsDao: BookDetailsDao,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val openAIApi: OpenAIApi
) : BookDetailsRepository {
    override suspend fun getBookDetails(
        bookId: String,
        bookTitle: String
    ): BookDetails {
        return bookDetailsDao.getBookDetails(bookId)?.toDomainModel()
            ?: bookDetailsApi.getBookDetails(
                bookId
            ).also {
                if (it.description?.value.isNullOrEmpty()) {
                    val bookAIDescription = getIABookDescription(bookTitle)
                    it.description = InconsistentType(
                        value = bookAIDescription,
                        generatedByAI = bookAIDescription.isNotEmpty()
                    )
                }
            }.toDomainModel(bookId)
    }

    override suspend fun getIABookDescription(bookTitle: String): String {
        return openAIApi.getOpenAIResult(
            OpenAIRequestBody(prompt = "$PROMPT_BOOK_DESCRIPTION$bookTitle")
        ).choices.firstOrNull()?.value.orEmpty()
    }

    override suspend fun getBookRatings(bookId: String): BookRatingsResponse =
        bookDetailsApi.getBookRatings(bookId)

    override suspend fun getBookAnalytics(bookId: String): BookAnalyticsResponse =
        bookDetailsApi.getBookAnalytics(bookId)

    override suspend fun saveBookDetails(bookDetailsCached: BookDetailsCached) {
        bookDetailsDao.insert(bookDetailsCached)
    }

    companion object {
        const val PROMPT_BOOK_DESCRIPTION = "Description for book: "
    }
}
