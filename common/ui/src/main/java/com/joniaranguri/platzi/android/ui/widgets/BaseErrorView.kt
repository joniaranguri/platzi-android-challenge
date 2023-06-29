package com.joniaranguri.platzi.android.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.joniaranguri.platzi.android.ui.R
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding


@Composable
fun BaseErrorView(e: Throwable, action: () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(2000)),
        exit = fadeOut(animationSpec = tween(2000))
    ) {
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.error_image)
                    .crossfade(500).build(),
                contentDescription = "Error image illustration",
                contentScale = ContentScale.Inside,
                modifier = Modifier.fillMaxWidth(),

                )
            TitleLarge(text = "Something went wrong")
            Text(text = e.message.orEmpty())
            Button(onClick = action) {
                Text("Retry")
            }
        }
    }
}
