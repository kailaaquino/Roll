package com.zybooks.roll.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.ui.Routes
import com.zybooks.roll.ui.components.ActivityCard
import com.zybooks.roll.viewmodel.DeckViewModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun CategoryDetailsScreen(
    navController: NavController,
    viewModel: DeckViewModel,
    categoryId: Int,
    onAddActivityClick: () -> Unit
){
    val activities = viewModel.getActivitiesForCategory(categoryId)
    val categoryName = viewModel.getCategoryNameById(categoryId)


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName + " Details",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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
            items(activities) { activity ->
                ActivityCard(
                    activity = activity,
                    isAddCard = false,
                    modifier = Modifier
                        .clickable {
                            Log.d("Detail screen", "Add category clicked")
                            navController.navigate(
                                Routes.ActivityDetails(activity.id)
                            )
                        }
                )
            }
            item {
                ActivityCard(
                    activity = ActivityItem(id = 0, name = "Add Activity", categoryId = 0),
                    isAddCard = true,
                    modifier = Modifier.clickable {
                        Log.d("Detail screen", "Add activity clicked")
                        onAddActivityClick()
                    }
                )
            }
        }
    }
}