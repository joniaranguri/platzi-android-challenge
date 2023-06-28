package com.joniaranguri.platzi.android.book_details.data.mapper

import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.AUTHORS_PATH_PREFIX
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.COVER_PREFIX
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.COVER_SUFFIX
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.DB_LIST_SEPARATOR
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.MAX_SUBJECTS
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.NO_BIO
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.NO_DESCRIPTION
import com.joniaranguri.platzi.android.book_details.data.mapper.BookDetailsMapperConstants.NO_NAME
import com.joniaranguri.platzi.android.book_details.data.remote.model.AuthorDetailResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookAnalyticsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookDetailsResponse
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookRatingsResponse
import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails

fun BookDetailsResponse.toDomainModel(bookId: String) = BookDetails(
    bookId = bookId,
    description = description?.value ?: NO_DESCRIPTION,
    subjects = subjects.orEmpty().take(MAX_SUBJECTS),
    authors = authors?.map {
        Author(
            id = it.author?.authorPath?.removePrefix(AUTHORS_PATH_PREFIX).orEmpty()
        )
    }.orEmpty(),
    ratingAverage = ratings?.average ?: 0f,
    ratingCount = ratings?.count ?: 0,
    wantToRead = analytics?.wantToRead ?: 0,
    currentlyReading = analytics?.currentlyReading ?: 0,
    alreadyRead = analytics?.alreadyRead ?: 0
)

fun AuthorDetailResponse.toDomainModel() = Author(
    id = id!!,
    name = name ?: NO_NAME,
    biography = bio?.value ?: NO_BIO,
    photo = photoIds?.firstOrNull()
        ?.let { photoId -> "$COVER_PREFIX$photoId$COVER_SUFFIX" }.orEmpty()
)

fun BookDetailsCached.toDomainModel() = BookDetails(
    bookId = id,
    description = description,
    subjects = if (subjects.isEmpty()) emptyList() else subjects.split(DB_LIST_SEPARATOR),
    authors = if (authors.isEmpty()) emptyList() else authors.split(DB_LIST_SEPARATOR)
        .map { Author(id = it) },
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

fun BookDetails.setRatings(bookRating: BookRatingsResponse) {
    bookRating.rating.let {
        ratingAverage = it?.average ?: 0f
        ratingCount = it?.count ?: 0
    }
}

fun Author.toEntityModel() = AuthorCached(
    id = id,
    name = name,
    biography = biography,
    photo = photo
)

fun BookDetails.setAnalytics(bookAnalytics: BookAnalyticsResponse) {
    bookAnalytics.bookAnalytics.let {
        wantToRead = it?.wantToRead ?: 0
        currentlyReading = it?.currentlyReading ?: 0
        alreadyRead = it?.alreadyRead ?: 0
    }
}

object BookDetailsMapperConstants {
    const val DB_LIST_SEPARATOR = "|"
    const val MAX_SUBJECTS = 20
    const val NO_DESCRIPTION = "No description provided"
    const val NO_NAME = "No name provided"
    const val NO_BIO = "No biography provided"
    const val COVER_PREFIX = "https://covers.openlibrary.org/a/id/"
    const val COVER_SUFFIX = "-L.jpg"
    const val AUTHORS_PATH_PREFIX = "/authors/"
}