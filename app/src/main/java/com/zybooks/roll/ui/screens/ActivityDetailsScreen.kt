package com.zybooks.roll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.viewmodel.DeckViewModel

@Composable
fun ActivityDetailsScreen(
    navController: NavController,
    viewModel: DeckViewModel,
    activity: ActivityItem
){
    val name = activity.name
    val address = activity.address
    val note = activity.notes
    val categoryName = viewModel.getCategoryNameById(activity.categoryId)


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .aspectRatio(0.75f)
                .padding(32.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            {
                Text(
                    text = categoryName,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Black
                )
                Text(
                    text = categoryName,
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
                )
                {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    if (!address.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = address,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    if (!note.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = note,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.popBackStack()
                    // to do function to mark as completed
                }
            ){
                Text("Mark as Completed")
            }
        }

    }

}