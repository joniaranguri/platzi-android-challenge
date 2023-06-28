package com.joniaranguri.platzi.android.books.list.domain.repository

import com.joniaranguri.platzi.android.books.list.domain.model.Book

interface BooksRepository {
    suspend fun getBookList(
        page: Int,
        loadSize: Int
    ): List<Book>

    suspend fun refreshBooks(page: Int, loadSize: Int) :List<Book>
}