package com.zybooks.roll.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.ui.components.ActivityDetailCard
import com.zybooks.roll.viewmodel.DeckViewModel

@Composable
fun ActivityDetailsScreen(
    navController: NavController,
    viewModel: DeckViewModel,
    activity: ActivityItem
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActivityDetailCard(activity = activity, viewModel = viewModel)

        Button(
            onClick = {
                viewModel.toggleActivityStatus(activity)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                if (activity.isCompleted) "Mark as Incomplete"
                else "Mark as Completed"
            )
        }
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text("Close")
        }
    }
}