package com.joniaranguri.platzi.android.books.list.data.repository

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.books.list.data.local.dao.BooksDao
import com.joniaranguri.platzi.android.books.list.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.books.list.data.mapper.toEntityModel
import com.joniaranguri.platzi.android.books.list.data.remote.api.BooksApi
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import com.joniaranguri.platzi.android.books.list.domain.repository.BooksRepository
import javax.inject.Inject


class BooksRepositoryImpl @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val booksApi: BooksApi,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val booksDao: BooksDao
) : BooksRepository {
    override suspend fun getBookList(
        page: Int,
        loadSize: Int
    ): List<Book> = booksDao.getBookList(limit = loadSize, offset = loadSize * (page - 1))
        .map { it.toDomainModel() }

    override suspend fun refreshBooks(page: Int, loadSize: Int): List<Book> {
        booksApi.getBookList(page = page, limit = loadSize)
            .books.orEmpty()
            .map { it.toEntityModel() }
            .also { bookList ->
                booksDao.insertOnlyNews(bookList)
            }
        return getBookList(page, loadSize)
    }

    override suspend fun deleteBooks() {
        booksDao.deleteBooks()
    }
}
