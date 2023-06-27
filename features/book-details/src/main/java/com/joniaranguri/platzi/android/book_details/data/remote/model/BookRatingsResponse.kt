package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.squareup.moshi.Json

data class RatingsResponse(
    @Json(name = "summary")
    val rating: BookRating
)

data class BookRating(
    @Json(name = "average")
    val average: Float,

    @Json(name = "count")
    val count: Int
)