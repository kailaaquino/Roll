package com.zybooks.roll.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zybooks.roll.data.model.Category

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    isAddCard: Boolean = false,
    onRollClick: (Long) -> Unit = {}
) {
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
                    text = if (isAddCard) "+" else "♠",
                    style = MaterialTheme.typography.displayMedium,
                    color = if (isAddCard) Color.Gray else Color.Black
                )
            }
        }
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleMedium,
            color = if (isAddCard) Color.Gray else Color.Black
        )
        OutlinedButton(
            onClick = { onRollClick(category.id)},
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),


        ) {
            Text("Roll")
        }
    }
}

