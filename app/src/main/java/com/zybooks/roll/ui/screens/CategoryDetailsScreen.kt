package com.zybooks.roll.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailsScreen(
    navController: NavController,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Category Details") }
            )
        }
    ) {
        innerPadding ->
        Text(
            text = "Category Details Screen",
            modifier = Modifier.padding(innerPadding)
        )

    }

}