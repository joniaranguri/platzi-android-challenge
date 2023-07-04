package com.joniaranguri.platzi.android.book_details.data.remote.model

import com.joniaranguri.platzi.android.core.data.remote.model.base.InconsistentType
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

    var id: String? = null,

    @Json(name = "bio")
    var bio: InconsistentType? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "photos")
    val photoIds: List<Long>? = null
)