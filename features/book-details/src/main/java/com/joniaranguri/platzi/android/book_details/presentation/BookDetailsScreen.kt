package com.joniaranguri.platzi.android.book_details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.ui.ext.defaultScreenPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    bookId: String,
    bookName: String,
    goBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { DetailsTopAppBar(title = bookName, goBack) }
    ) { innerPadding ->
        BookDetailsContent(
            bookId = bookId,
            modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BookDetailsContent(bookId: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .defaultScreenPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Book Id : $bookId",
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsTopAppBar(title: String, goBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = goBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia arriba")
            }
        },
        title = { Text(text = title) }
    )
}
