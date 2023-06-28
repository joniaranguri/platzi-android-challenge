package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.joniaranguri.platzi.android.core.data.remote.model.InconsistentType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class AuthorRawResponse(
    @Json(name = "author")
    val author: AuthorKey?
)

data class AuthorKey(
    @Json(name = "key")
    val authorPath: String?
)

@JsonClass(generateAdapter = true)
data class AuthorDetailResponse(

    var id: String?,

    @Json(name = "bio")
    val bio: InconsistentType?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "photos")
    val photoIds: List<Long>?
)