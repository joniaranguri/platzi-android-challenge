package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.squareup.moshi.Json

data class AuthorRawResponse(
    @Json(name = "author")
    val author: AuthorKey
)

data class AuthorKey(
    @Json(name = "key")
    val authorPath: String
)

data class AuthorDetailResponse(
    val id: String,

    @Json(name = "bio")
    val bio: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "photos")
    val photoIds: List<Long>
)