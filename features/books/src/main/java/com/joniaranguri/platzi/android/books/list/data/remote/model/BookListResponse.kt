package com.joniaranguri.platzi.android.books.list.data.remote.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookListResponse(
    @Json(name = "works")
    val books: List<BookResponse>?
)

