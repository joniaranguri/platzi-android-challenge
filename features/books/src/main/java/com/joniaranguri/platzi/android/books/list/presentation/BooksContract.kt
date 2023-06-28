package com.joniaranguri.platzi.android.books.list.presentation

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class BooksViewState(
    val pagedData: Flow<PagingData<Book>> = emptyFlow(),
)

sealed class BooksEvent {
    object LoadBooks : BooksEvent()
}