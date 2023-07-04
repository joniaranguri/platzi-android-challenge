package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.joniaranguri.platzi.android.core.data.remote.model.base.InconsistentType
import com.squareup.moshi.Json

data class BookDetailsResponse(
    @Json(name = "description")
    var description: InconsistentType? = null,

    @Json(name = "subjects")
    val subjects: List<String>? = null,

    @Json(name = "authors")
    val authors: List<AuthorRawResponse>? = null,

    val ratings: BookRating? = null,

    val analytics: BookAnalytics? = null
)
