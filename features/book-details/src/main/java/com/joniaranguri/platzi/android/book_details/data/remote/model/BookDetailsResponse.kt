package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.joniaranguri.platzi.android.core.data.remote.model.InconsistentType
import com.squareup.moshi.Json

data class BookDetailsResponse(
    @Json(name = "description")
    val description: InconsistentType?,

    @Json(name = "subjects")
    val subjects: List<String>?,

    @Json(name = "authors")
    val authors: List<AuthorRawResponse>?,

    val ratings: BookRating?,

    val analytics: BookAnalytics?
)
