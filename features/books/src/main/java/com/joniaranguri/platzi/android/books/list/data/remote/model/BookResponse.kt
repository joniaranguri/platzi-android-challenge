package com.joniaranguri.platzi.android.books.list.data.remote.model

import com.squareup.moshi.Json

data class BookResponse(

    @Json(name = "key")
    val key: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "first_publish_year")
    val firstPublishYear: Int,

    @Json(name = "cover_i")
    val coverImageId: Long,

    @Json(name = "author_name")
    val authorsList: List<String>

)
