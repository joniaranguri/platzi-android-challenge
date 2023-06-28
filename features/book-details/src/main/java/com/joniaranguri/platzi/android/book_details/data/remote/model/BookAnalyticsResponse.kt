package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookAnalyticsResponse(
    @Json(name = "counts")
    val bookAnalytics: BookAnalytics?
)

data class BookAnalytics(
    @Json(name = "want_to_read")
    val wantToRead: Int?,

    @Json(name = "currently_reading")
    val currentlyReading: Int?,

    @Json(name = "already_read")
    val alreadyRead: Int?
)
