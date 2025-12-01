package com.zybooks.roll.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zybooks.roll.data.model.Category
import com.zybooks.roll.ui.Routes
import com.zybooks.roll.ui.components.CategoryCard
import com.zybooks.roll.viewmodel.DeckViewModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun DeckScreen(
    navController: NavController,
    viewModel: DeckViewModel = viewModel(),
    onAddCategoryClick: () -> Unit
    ) {
    val categories = viewModel.categories
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Deck",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                    ) }
            )
        }
    ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                Routes.CategoryDetail(categoryId = category.id)
                            )
                        },
                        isAddCard = false,
                        onRollClick = { categoryId ->
                            val rolled = viewModel.rollActivityFromCategory(categoryId)
                            Log.d("DeckScreen", "Rolled: ${rolled?.name}")
                        }
                    )
                }
                item {
                    CategoryCard(
                        category = Category(id = 0, name = "Create Category"),
                        isAddCard = true,
                        modifier = Modifier.clickable {
                            Log.d("Deck screen", "Add category clicked")
                            onAddCategoryClick()
                        }
                    )
                }
            }
        }

}
