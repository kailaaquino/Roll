package com.zybooks.roll.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.zybooks.roll.data.model.Category

@Composable
fun CreateCategoryScreen(
    navController: NavController
){
    var categoryName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Create a new category",
            style = MaterialTheme.typography.titleLarge
        )
//        OutlinedTextField(
//            value = categoryName,
//            onValueChange = { categoryName = it },
//            label = { Text("Category Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
        Button(onClick = {
            navController.popBackStack() // Go back to DeckScreen
        }) {
            Text("Save")
        }
    }
}