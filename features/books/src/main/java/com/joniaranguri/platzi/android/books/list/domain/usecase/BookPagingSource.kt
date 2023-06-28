package com.joniaranguri.platzi.android.books.list.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import com.joniaranguri.platzi.android.books.list.domain.repository.BooksRepository
import java.io.IOException


class BookPagingSource(
    internal val repository: BooksRepository,
) : PagingSource<Int, Book>() {

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val page = params.key ?: 1
        return try {
            val bookList = repository.getBookList(page,  params.loadSize).ifEmpty { repository.refreshBooks(page, params.loadSize) }
            LoadResult.Page(
                data = bookList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (bookList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}