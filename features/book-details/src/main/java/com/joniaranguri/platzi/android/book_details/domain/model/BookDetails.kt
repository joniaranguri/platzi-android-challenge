package com.joniaranguri.platzi.android.book_details.domain.model

data class BookDetails(
    val bookId: String,
    val description: String,
    val descriptionByAI: Boolean = false,
    val subjects: List<String>,
    var authors: List<Author> = emptyList(),
    var ratingAverage: Float,
    var ratingCount: Int,
    var wantToRead: Int,
    var currentlyReading: Int,
    var alreadyRead: Int
)

fun BookDetails.isNotInDB() =
    (ratingAverage == 0f).and(ratingCount == 0).and(wantToRead == 0).and(currentlyReading == 0)
        .and(alreadyRead == 0)