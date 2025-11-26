package com.zybooks.roll.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.zybooks.roll.data.model.ActivityItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ActivityCard(
    activity: ActivityItem,
    modifier: Modifier = Modifier,
    isAddCard: Boolean = false,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isAddCard) "+" else "♣",
                    style = MaterialTheme.typography.displayMedium,
                    color = if (isAddCard) Color.Gray else Color.Black
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            Text(
                text = activity.name,
                // if (isAddCard) "Add" else category.name,
                style = MaterialTheme.typography.titleMedium,
                color = if (isAddCard) Color.Gray else Color.Black
            )

            Text(
                text = if (activity.isCompleted) "Completed" else "Incomplete",
                style = MaterialTheme.typography.titleMedium
            )
        }

    }


}