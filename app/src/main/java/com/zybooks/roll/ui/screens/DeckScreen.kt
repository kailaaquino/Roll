package com.zybooks.roll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zybooks.roll.data.model.Category
import com.zybooks.roll.ui.components.CategoryCard

@Composable
fun DeckScreen(navController: NavController) {
    val categories = listOf(
        Category(1, "Restaurants"),
        Category(2, "Hikes"),
        Category(-1, "Add Category") // special item
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(categories) { category ->
                if (category.id == -1) {
                    CategoryCard(
                        category = category,
                        isAddCard = true
                    )
                } else {
                    CategoryCard(
                        category = category
                    )
                }
            }
        }
    }
}
