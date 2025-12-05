package com.zybooks.roll.ui.screens

import com.zybooks.roll.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.ui.Routes
import com.zybooks.roll.ui.animations.DiceAnimation
import com.zybooks.roll.viewmodel.DeckViewModel
import com.zybooks.roll.viewmodel.RollViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun RollScreen(
    navController: NavController,
    deckViewModel: DeckViewModel = viewModel(),
    rollViewModel: RollViewModel = viewModel()
    ) {
    val coroutineScope = rememberCoroutineScope()
    var isRolling by remember { mutableStateOf(false) }
    var rolledActivity by remember { mutableStateOf<ActivityItem?>(null) }
    var rollMessage by remember { mutableStateOf("") }

    val shouldRoll by rollViewModel.shouldRoll.collectAsState()
    LaunchedEffect(Unit) {
        rollViewModel.enableShakeDetection()
    }

    DisposableEffect(Unit) {
        onDispose {
            rollViewModel.disableShakeDetection()
        }
    }
    // When shake detected, perform roll
    LaunchedEffect(shouldRoll) {
        if (shouldRoll) {
            isRolling = true
            delay(2500)

            val result = deckViewModel.rollAnyActivity()

            if (result == null) {
                rollMessage = "All activities are completed! Add more to keep rolling."
            } else {
                navController.navigate(Routes.RolledActivity(result.id, null))
            }

            rollViewModel.resetRollFlag()
//            isRolling = false
        }
    }

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
            if (isRolling){
                DiceAnimation()
            } else {
                Image(
                    painter = painterResource(R.drawable.dice_image),
                    contentDescription = "Dice",
                    modifier = Modifier
                        .fillMaxSize(0.90f)
                        .clickable {
                            isRolling = true
                            coroutineScope.launch {
                                delay(2500)
                                val result = deckViewModel.rollAnyActivity()
                                if (result != null) {
                                    navController.navigate(Routes.RolledActivity(result.id))
                                }
                                else{
                                    rollMessage = "All activities are completed! Add more to keep rolling."
                                }
                            }
                        }
                )
            }
        }
    }
}