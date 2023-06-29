package com.joniaranguri.platzi.android.books.list.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.joniaranguri.platzi.android.books.list.domain.usecase.DeleteBooks
import com.joniaranguri.platzi.android.books.list.domain.usecase.GetBooks
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooks: GetBooks,
    private val deleteBooks: DeleteBooks
) : MviViewModel<BaseViewState<BooksViewState>, BooksEvent>() {
    private val config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)
    override fun onTriggerEvent(eventType: BooksEvent) {
        when (eventType) {
            is BooksEvent.LoadBooks -> onLoadBooks()
            is BooksEvent.RefreshBooks -> onRefreshBooks()
        }
    }

    private fun onLoadBooks() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetBooks.Params(config)
        val pagedFlow = getBooks(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(BooksViewState(pagedData = pagedFlow)))
    }

    private fun onRefreshBooks() = safeLaunch {
        setState(BaseViewState.Loading)
        call(deleteBooks(Unit)) {
            onTriggerEvent(BooksEvent.LoadBooks)
        }
    }

    companion object {
        const val PAGE_SIZE = 9
    }
}