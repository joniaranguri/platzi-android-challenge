package com.joniaranguri.platzi.android.books.list.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.books.list.data.repository.mockedBooks
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.ext.defaultScreenPadding

@Composable
fun BookListContent(
    modifier: Modifier,
    goToBookDetails: (bookName: String, bookId: String) -> Unit,
    bookList: List<Book> = mockedBooks
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .defaultScreenPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(defaultPadding()),
    ) {
        items(bookList.size) { index ->
            BookRow(
                dto = bookList[index],
                onBookClick = goToBookDetails
            )
        }
    }
}


