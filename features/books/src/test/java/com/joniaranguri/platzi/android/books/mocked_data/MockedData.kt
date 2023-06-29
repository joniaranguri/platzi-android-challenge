package com.joniaranguri.platzi.android.books.mocked_data

import com.joniaranguri.platzi.android.books.list.domain.model.Book

object MockedData {
    fun getBookList(): List<Book> = listOf(
        Book(
            id = "A8SA9F",
            title = "First Book",
            firstPublishYear = 2023,
            authors = "Robert Kiyosaki",
            coverImageUrl = "https://image1.jpg"
        ),
        Book(
            id = "A8A3SA9F",
            title = "Second Book",
            firstPublishYear = 1700,
            authors = "Miguel de Cervantes Saavedra",
            coverImageUrl = "https://image2.jpg"
        )
    )
}