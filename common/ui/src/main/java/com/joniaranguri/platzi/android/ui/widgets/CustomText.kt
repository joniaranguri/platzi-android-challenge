package com.joniaranguri.platzi.android.ui.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.joniaranguri.platzi.android.theme.CeraPro
import de.charlex.compose.HtmlText


@Composable
fun TitleSmall(text: String, maxLines: Int = 1) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun TitleMedium(text: String, maxLines: Int = 2) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TitleLarge(text: String, maxLines: Int = 2, textAlign: TextAlign? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}

@Composable
fun HtmlText(text: String, modifier: Modifier = Modifier) {
    HtmlText(
        modifier = modifier,
        text = text,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
        letterSpacing = MaterialTheme.typography.bodyMedium.letterSpacing,
        fontFamily = CeraPro, color = MaterialTheme.colorScheme.onBackground
    )
}