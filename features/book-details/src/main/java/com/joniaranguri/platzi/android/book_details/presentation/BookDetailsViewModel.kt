package com.joniaranguri.platzi.android.book_details.presentation

import com.joniaranguri.platzi.android.book_details.domain.usecase.GetBookDetails
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookDetails: GetBookDetails
) : MviViewModel<BaseViewState<BookDetailsViewState>, BookDetailsEvent>() {

    override fun onTriggerEvent(eventType: BookDetailsEvent) {
        when (eventType) {
            is BookDetailsEvent.LoadDetail -> onLoadDetail(eventType.bookId)
        }
    }

    private fun onLoadDetail(bookId: String) = safeLaunch {
        val params = GetBookDetails.Params(bookId = bookId)
        execute(getBookDetails(params)) { dto ->
            setState(BaseViewState.Data(BookDetailsViewState(bookDetails = dto)))
        }
    }
}