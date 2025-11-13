package com.zybooks.roll.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zybooks.roll.data.model.Category

@Composable
fun CreateCategoryScreen(
    navController: NavController
) {
    var categoryName by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .aspectRatio(0.75f)
                .padding(32.dp)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "♠",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Black
                )
                Text(
                    text = "♠",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Black
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = "Create a new category",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    TextField(
                        value = categoryName,
                        onValueChange = { categoryName = it },
                        label = { Text("Category Name") },
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                    {
                        Text("Add Category")
                    }
                }
            }
        }
    }
}