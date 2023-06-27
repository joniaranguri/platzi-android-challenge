package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.widgets.HtmlText

@Composable
fun AuthorList(authorList: List<Author>) {
    Column(
        modifier = Modifier.padding(bottom = defaultPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow {
            authorList.forEach {
                item {
                    AuthorCard(
                        author = it,
                        modifier = Modifier.fillParentMaxWidth(if (authorList.size == 1) 1f else .8f)
                    )
                }
            }
        }
    }
}

@Composable
fun AuthorCard(author: Author, modifier: Modifier) {
    Card(
        modifier = modifier.padding(horizontal = defaultPadding()),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .defaultContentPadding()
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.wrapContentSize(),
                color = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    text = "Authors",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = author.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(defaultPadding())
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(author.photo)
                        .crossfade(500).build(),
                    contentDescription = "contentDescription",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                HtmlText(
                    text = author.biography
                )
            }
        }
    }

}
