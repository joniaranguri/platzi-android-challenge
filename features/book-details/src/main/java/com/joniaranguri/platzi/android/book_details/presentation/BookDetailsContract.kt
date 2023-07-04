package com.joniaranguri.platzi.android.book_details.presentation

import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails

data class BookDetailsViewState(
    val bookDetails: BookDetails? = null
)

sealed class BookDetailsEvent {
    data class LoadDetail(val bookId: String, val bookTitle: String) : BookDetailsEvent()
}