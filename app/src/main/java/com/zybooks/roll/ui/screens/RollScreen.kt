package com.zybooks.roll.ui.screens

import com.zybooks.roll.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.viewmodel.DeckViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RollScreen(
    viewModel: DeckViewModel = viewModel(),
    ) {
    var rolledActivity by remember { mutableStateOf<ActivityItem?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Roll",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                ) }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Shake or tap to roll!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(R.drawable.dice),
                contentDescription = "Dice",
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .clickable{
                        rolledActivity = viewModel.rollAnyActivity()
                    }
            )
            rolledActivity?.let { activity ->
                Text(
                    text = "You got: ${activity.name}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}