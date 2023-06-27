package com.joniaranguri.platzi.android.book_details.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.joniaranguri.platzi.android.book_details.data.repository.mockedDetails
import com.joniaranguri.platzi.android.book_details.presentation.view.BookDetailsContent
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    bookId: String,
    title: String,
    coverImageUrl: String,
    goBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { DetailsTopAppBar(title = title, goBack) }
    ) { innerPadding ->
        BookDetailsContent(
            title = title,
            coverImageUrl = coverImageUrl,
            bookDetails = mockedDetails,
            modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsTopAppBar(title: String, goBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = goBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
            }
        },
        title = { Text(text = NavigationItem.BookDetails.title) }
    )
}
