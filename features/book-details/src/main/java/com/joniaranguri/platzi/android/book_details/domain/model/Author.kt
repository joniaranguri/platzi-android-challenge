package com.joniaranguri.platzi.android.book_details.domain.model

data class Author(
    val id: String,
    val name: String = "",
    val biography: String = "",
    val photo: String = ""
)