package com.joniaranguri.platzi.android.books.list.domain.usecase

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import com.joniaranguri.platzi.android.books.list.domain.repository.BooksRepository
import com.joniaranguri.platzi.android.core.usecase.FlowPagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooks @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: BooksRepository
) : FlowPagingUseCase<GetBooks.Params, Book>() {

    data class Params(
        val pagingConfig: PagingConfig,
    )

    override fun execute(params: Params): Flow<PagingData<Book>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { BookPagingSource(repository) }
        ).flow
    }
}