package com.joniaranguri.platzi.android.book_details.domain.usecase

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.book_details.data.mapper.setAnalytics
import com.joniaranguri.platzi.android.book_details.data.mapper.setRatings
import com.joniaranguri.platzi.android.book_details.data.mapper.toEntityModel
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails
import com.joniaranguri.platzi.android.book_details.domain.model.isNotInDB
import com.joniaranguri.platzi.android.book_details.domain.repository.AuthorDetailsRepository
import com.joniaranguri.platzi.android.book_details.domain.repository.BookDetailsRepository
import com.joniaranguri.platzi.android.core.network.DataState
import com.joniaranguri.platzi.android.core.network.apiCall
import com.joniaranguri.platzi.android.core.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetBookDetails
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val bookDetailsRepository: BookDetailsRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val authorDetailsRepository: AuthorDetailsRepository
) : DataStateUseCase<GetBookDetails.Params, BookDetails>() {

    data class Params(
        val bookId: String,
        val bookTitle: String
    )

    override suspend fun FlowCollector<DataState<BookDetails>>.execute(params: Params) {
        val bookId = params.bookId
        val bookTitle = params.bookTitle
        val bookDetailsDataState =
            apiCall { bookDetailsRepository.getBookDetails(bookId = bookId, bookTitle = bookTitle) }
        bookDetailsDataState.map { bookDetails ->
            if (bookDetails.isNotInDB()) {
                val bookAnalytics = bookDetailsRepository.getBookAnalytics(bookId)
                val bookRatings = bookDetailsRepository.getBookRatings(bookId)
                bookDetails.setRatings(bookRatings)
                bookDetails.setAnalytics(bookAnalytics)
            }
            val authorDataState =
                apiCall { authorDetailsRepository.getAuthorsDetails(bookDetails.authors.map { it.id }) }
            if (authorDataState is DataState.Success) {
                bookDetails.authors = authorDataState.result
            }
            saveBookDetails(bookDetails)
        }

        emit(bookDetailsDataState)
    }

    private suspend fun saveBookDetails(bookDetailsEntity: BookDetails) {
        bookDetailsRepository.saveBookDetails(bookDetailsEntity.toEntityModel())
        bookDetailsEntity.authors.forEach { author ->
            authorDetailsRepository.saveAuthorDetails(author.toEntityModel())
        }
    }
}