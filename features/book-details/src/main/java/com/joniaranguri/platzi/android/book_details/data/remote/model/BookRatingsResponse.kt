package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class BookRatingsResponse(
    @Json(name = "summary")
    val rating: BookRating?
)

@JsonClass(generateAdapter = true)
data class BookRating(
    @Json(name = "average")
    val average: Float?,

    @Json(name = "count")
    val count: Int?
)