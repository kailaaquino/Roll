package com.zybooks.roll.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun DeckScreen(
    navController: NavController,
    viewModel: DeckViewModel,
    onAddCategoryClick: () -> Unit
) {
    val categories by viewModel.categories.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var selectionMode by remember { mutableStateOf(false) }
    val selectedCategories = remember { mutableStateListOf<Long>() }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            if (selectionMode) "Select Categories" else "Your Deck",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        if (!selectionMode) {
                            IconButton(onClick = { selectionMode = true }) {
                                Icon(
                                    imageVector = Icons.Default.CheckBox,
                                    contentDescription = "Select Categories"
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                selectedCategories.clear()
                                selectionMode = false
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cancel Selection"
                                )
                            }
                        }
                    }
                )

                if (selectionMode) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                selectedCategories.forEach { id ->
                                    categories.find { it.id == id }?.let { viewModel.deleteCategory(it) }
                                }
                                selectedCategories.clear()
                                selectionMode = false
                            }
                        ) {
                            Text("Delete")
                        }

                        if (selectedCategories.size == 1) {
                            Button(
                                onClick = {
                                    val id = selectedCategories.first()
                                    navController.navigate(Routes.CreateCategory(id))
                                }
                            ) {
                                Text("Edit")
                            }
                        }

                        Button(
                            onClick = {
                                selectedCategories.clear()
                                selectionMode = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
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
                val isSelected = selectedCategories.contains(category.id)

                CategoryCard(
                    category = category,
                    isAddCard = false,
                    showSelection = selectionMode,
                    isSelected = isSelected,
                    modifier = Modifier,
                    onClick = {
                        if (!selectionMode) {
                            navController.navigate(Routes.CategoryDetail(category.id))
                        } else {
                            if (isSelected) {
                                selectedCategories.remove(category.id)
                                if (selectedCategories.isEmpty()) selectionMode = false
                            } else {
                                selectedCategories.add(category.id)
                            }
                        }
                    },
                    onRollClick = { categoryId ->
                        if (!selectionMode) {
                            coroutineScope.launch {
                                val rolled = viewModel.rollActivityFromCategorySuspending(categoryId)
                                rolled?.let {
                                    navController.navigate(
                                        Routes.RolledActivity(
                                            it.id,
                                            categoryId,
                                            "deck"
                                        )
                                    )
                                }
                            }
                        }
                    }
                )
            }

            item {
                CategoryCard(
                    category = Category(id = -1, name = "Create Category"),
                    isAddCard = true,
                    showSelection = false,
                    isSelected = false,
                    onClick = { onAddCategoryClick() }
                )
            }

        }
    }
}