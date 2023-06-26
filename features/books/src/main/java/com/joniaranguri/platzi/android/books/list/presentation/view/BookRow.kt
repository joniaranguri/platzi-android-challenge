package com.joniaranguri.platzi.android.books.list.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joniaranguri.platzi.android.books.R
import com.joniaranguri.platzi.android.books.list.domain.model.Book
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.widgets.TitleLarge
import com.joniaranguri.platzi.android.ui.widgets.TitleSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRow(
    dto: Book,
    onBookClick: (bookName: String, bookId: String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onBookClick(dto.title, dto.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
        )
    ) {
        Row(
            modifier = Modifier.height(130.dp),
        ) {
            Column(
                modifier = Modifier
                    .defaultContentPadding()
                    .fillMaxWidth(.7f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center

            ) {
                TitleSmall(text = dto.authorName.first())
                TitleLarge(text = dto.title)
                Text(text = dto.firstPublishYear.toString())
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = rememberAsyncImagePainter(R.drawable.half_circle_right),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.CenterEnd,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dto.coverImageUrl)
                        .crossfade(500).build(),
                    contentDescription = "${dto.title}'s cover image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxHeight()
                        .defaultContentPadding()
                )
            }
        }
    }
}