package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.widgets.CardFace
import com.joniaranguri.platzi.android.ui.widgets.FlipCard
import com.joniaranguri.platzi.android.ui.widgets.RatingBar
import com.joniaranguri.platzi.android.ui.widgets.TitleLarge
import com.joniaranguri.platzi.android.ui.widgets.TitleMedium
import com.joniaranguri.platzi.android.ui.widgets.TitleSmall


@Composable
fun FlipDetails(bookDetails: BookDetails, title: String, coverImageUrl: String) {
    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }
    FlipCard(
        frontCard = { FrontCard(title, coverImageUrl, bookDetails) },
        backCard = { BackCard(bookDetails) },
        color = MaterialTheme.colorScheme.background,
        cardFace = cardFace,
        onClick = { cardFace = cardFace.next },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = defaultPadding()),
    )
}

@Composable
fun BackCard(bookDetails: BookDetails) {
    Text(text = bookDetails.description)
}

@Composable
fun FrontCard(title: String, coverImageUrl: String, bookDetails: BookDetails) {
    val bookSize = LocalConfiguration.current.screenWidthDp.dp * .7F
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coverImageUrl)
                .crossfade(500).build(),
            contentDescription = "$title's cover image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(.5F)
                .defaultMinSize(bookSize, bookSize)
        )
        TitleLarge(text = title, maxLines = 10, textAlign = TextAlign.Center)
        RatingBar(rating = bookDetails.ratingAverage.toDouble())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleSmall(text = "${bookDetails.wantToRead} want to read")
            TitleSmall(text = "${bookDetails.currentlyReading} currently reading")
            TitleSmall(text = "${bookDetails.alreadyRead} have read")
        }
        TitleMedium(text = "Tap to see details")
    }
}

