package com.joniaranguri.platzi.android.books.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.ui.ext.defaultScreenPadding
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    goToBookDetails: (bookName: String, bookId: String) -> Unit,
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

@Composable
fun BookListContent(
    modifier: Modifier,
    goToBookDetails: (bookName: String, bookId: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .defaultScreenPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Book List Screen",
            modifier = Modifier
        )
        Button(onClick = { goToBookDetails("Rich Dad", "23232") }) {
            Text(
                text = "Go to detail screen",
                modifier = Modifier
            )
        }

    }
}

