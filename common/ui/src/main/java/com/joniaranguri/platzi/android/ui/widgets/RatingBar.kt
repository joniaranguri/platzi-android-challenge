package com.joniaranguri.platzi.android.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.floor
import androidx.compose.material.icons.twotone.Star


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = stars - filledStars
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.TwoTone.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}
