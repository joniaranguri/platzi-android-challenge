package com.joniaranguri.platzi.android.books.list.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.books.list.presentation.view.BookListContent
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    goToBookDetails: (bookName: String, bookId: String, coverImage: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = NavigationItem.BookList.title) })
        }
    ) { innerPadding ->
        BookListContent(modifier.padding(innerPadding), goToBookDetails)
    }
}

