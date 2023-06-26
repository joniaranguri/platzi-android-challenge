package com.joniaranguri.platzi.android.book_details.data.mapper

import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookDetailsResponse
import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails

const val DB_LIST_SEPARATOR = "|"

fun BookDetailsResponse.toDomainModel(bookId: String) = BookDetails(
    bookId = bookId,
    description = description,
    subjects = subjects,
    authors = authorsDetails.map { authorDetail ->
        Author(
            id = authorDetail.id,
            name = authorDetail.name,
            biography = authorDetail.bio,
            photo = authorDetail.photoIds.firstOrNull()
                ?.let { photoId -> "https://covers.openlibrary.org/a/id/$photoId-L.jpg" }.orEmpty()
        )
    },
    ratingAverage = ratings.average,
    ratingCount = ratings.count,
    wantToRead = analytics.wantToRead,
    currentlyReading = analytics.currentlyReading,
    alreadyRead = analytics.alreadyRead
)

fun BookDetailsCached.toDomainModel() = BookDetails(
    bookId = id,
    description = description,
    subjects = subjects.split(DB_LIST_SEPARATOR),
    ratingAverage = ratingAverage,
    ratingCount = ratingCount,
    wantToRead = wantToRead,
    currentlyReading = currentlyReading,
    alreadyRead = alreadyRead
)

fun AuthorCached.toDomainModel() = Author(
    id = id,
    name = name,
    biography = biography,
    photo = photo
)

fun BookDetails.toEntityModel() = BookDetailsCached(
    id = bookId,
    description = description,
    subjects = subjects.joinToString(separator = DB_LIST_SEPARATOR),
    authors = authors.joinToString(DB_LIST_SEPARATOR) { author -> author.id },
    ratingAverage = ratingAverage,
    ratingCount = ratingCount,
    wantToRead = wantToRead,
    currentlyReading = currentlyReading,
    alreadyRead = alreadyRead
)

fun Author.toEntityModel() = AuthorCached(
    id = id,
    name = name,
    biography = biography,
    photo = photo
)
