package com.joniaranguri.platzi.android.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.joniaranguri.platzi.android.ui.R
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding


@Composable
fun BaseErrorView(e: Throwable, action: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .defaultContentPadding(),
        verticalArrangement = Arrangement.spacedBy(
            defaultPadding(),
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleLarge(text = "!Ups!")
        Image(
            painter = rememberAsyncImagePainter(R.drawable.error_image),
            contentDescription = "Error image illustration",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Inside
        )
        TitleLarge(text = "Something went wrong")
        Text(text = e.message.orEmpty())
        Button(onClick = action) {
            Text("Retry")
        }
    }
}
