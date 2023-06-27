package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joniaranguri.platzi.android.ui.ext.defaultPadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubjectList(subjectList: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxSize().padding(horizontal = defaultPadding()),
        horizontalArrangement = Arrangement.Start,
        maxItemsInEachRow = 5
    ) {
        subjectList.forEach { subject ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onTertiary),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = subject,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .wrapContentWidth()
                )
            }
        }

    }
}
