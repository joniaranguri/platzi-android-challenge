package com.joniaranguri.platzi.android.books.list.data.mapper

import com.joniaranguri.platzi.android.books.list.data.local.model.BookCached
import com.joniaranguri.platzi.android.books.list.data.remote.model.BookResponse
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import java.sql.Timestamp

fun BookResponse.toEntityModel() = BookCached(
    id = key?.removePrefix("/works/").orEmpty(),
    title = title.orEmpty(),
    firstPublishYear = firstPublishYear?:0,
    coverImageUrl = "https://covers.openlibrary.org/b/id/$coverImageId-L.jpg",
    timestamp = System.currentTimeMillis(),
    authors = authorsList.orEmpty().joinToString()
)

fun BookCached.toDomainModel() = Book(
    id = id,
    title = title,
    firstPublishYear = firstPublishYear,
    coverImageUrl = coverImageUrl,
    authors = authors
)
