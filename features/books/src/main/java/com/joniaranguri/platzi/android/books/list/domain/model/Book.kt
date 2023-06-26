package com.joniaranguri.platzi.android.books.list.domain.model

data class Book(
    val id: String,
    val title: String,
    val firstPublishYear: Int,
    val coverImageUrl: String,
    val authorName: List<String>
)
