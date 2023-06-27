package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails
import com.joniaranguri.platzi.android.ui.widgets.TitleLarge


@Composable
fun BookDetailsContent(
    title: String,
    coverImageUrl: String,
    bookDetails: BookDetails,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        FlipDetails(bookDetails, title, coverImageUrl)

        TitleLarge(text = "Topics")
        SubjectList(subjectList = bookDetails.subjects)

        TitleLarge(text = "About the authors")
        AuthorList(authorList = bookDetails.authors)
    }

}