package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.squareup.moshi.Json

data class BookDetailsResponse(
    @Json(name = "description")
    val description: String,

    @Json(name = "subjects")
    val subjects: List<String>,

    @Json(name = "authors")
    val authors: List<AuthorRawResponse>,

    val ratings: BookRating,

    val authorsDetails: List<AuthorDetailResponse>,

    val analytics: BookAnalytics
)
