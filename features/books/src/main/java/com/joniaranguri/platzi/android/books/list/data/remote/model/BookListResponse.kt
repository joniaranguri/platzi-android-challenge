package com.joniaranguri.platzi.android.books.list.data.remote.model
import com.squareup.moshi.Json

data class BookListResponseBook(
    @Json(name = "works")
    val id: List<BookResponse>
)

