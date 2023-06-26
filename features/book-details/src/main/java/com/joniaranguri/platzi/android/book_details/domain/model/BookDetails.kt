package com.joniaranguri.platzi.android.book_details.domain.model

data class BookDetails(
    val bookId:String,
    val description: String,
    val subjects: List<String>,
    val authors: List<Author> = emptyList(),
    val ratingAverage: Float,
    val ratingCount: Int,
    val wantToRead: Int,
    val currentlyReading: Int,
    val alreadyRead: Int
)