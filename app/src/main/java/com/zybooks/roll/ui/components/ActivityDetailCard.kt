package com.zybooks.roll.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.viewmodel.DeckViewModel

@Composable
fun ActivityDetailCard(
    activity: ActivityItem,
    viewModel: DeckViewModel,
) {
    val name = activity.name
    val address = activity.address
    val note = activity.notes
    val categoryName = viewModel.getCategoryNameById(activity.categoryId)

        Card(
            modifier = Modifier
                .aspectRatio(0.75f)
                .padding(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = categoryName,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )

                Text(
                    text = categoryName,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    if (!address.isNullOrBlank()) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = address,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                    if (!note.isNullOrBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = note,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
}