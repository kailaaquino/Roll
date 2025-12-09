package com.zybooks.roll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zybooks.roll.data.RollRepository
import com.zybooks.roll.sensors.AccelerometerSensor
import com.zybooks.roll.ui.RollApp
import com.zybooks.roll.ui.screens.DeckScreen
import com.zybooks.roll.ui.theme.RollTheme
import com.zybooks.roll.viewmodel.DeckViewModel
import com.zybooks.roll.viewmodel.RollViewModel


class MainActivity : ComponentActivity() {

    private lateinit var accelerometer: AccelerometerSensor
    private val rollViewModel: RollViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = RollRepository(applicationContext)

        val deckViewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DeckViewModel::class.java)) {
                    return DeckViewModel(repo) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
        val deckViewModel: DeckViewModel =
            ViewModelProvider(this, deckViewModelFactory)
                .get(DeckViewModel::class.java)

        enableEdgeToEdge()

        // Initialize accelerometer
        accelerometer = AccelerometerSensor(this)

        // Start listening for accelerometer updates
        accelerometer.startListening { values ->
            rollViewModel.onAccelerometerChanged(values)
        }

        setContent {
            RollTheme {
                RollApp(
                    deckViewModel = deckViewModel,
                    rollViewModel = rollViewModel
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        accelerometer.stopListening()
    }

    override fun onResume() {
        super.onResume()
        accelerometer.startListening { values ->
            rollViewModel.onAccelerometerChanged(values)
        }
    }
}
