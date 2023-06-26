package com.joniaranguri.platzi.android.books.list.data.mapper

import com.joniaranguri.platzi.android.books.list.data.local.model.BookCached
import com.joniaranguri.platzi.android.books.list.data.remote.model.BookResponse
import com.joniaranguri.platzi.android.books.list.domain.model.Book

fun BookResponse.toDomainModel() = Book(
    id = key.removePrefix("/works/"),
    title = title,
    firstPublishYear = firstPublishYear,
    coverImageUrl = "https://covers.openlibrary.org/b/id/$coverImageId-L.jpg",
    authors = authorsList.joinToString()
)

fun BookCached.toDomainModel() = Book(
    id = id,
    title = title,
    firstPublishYear = firstPublishYear,
    coverImageUrl = coverImageUrl,
    authors = authors
)

fun Book.toEntityModel() = BookCached(
    id = id,
    title = title,
    firstPublishYear = firstPublishYear,
    coverImageUrl = coverImageUrl,
    authors = authors
)
