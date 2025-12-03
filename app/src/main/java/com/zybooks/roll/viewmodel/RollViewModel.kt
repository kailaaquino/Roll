package com.zybooks.roll.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs

class RollViewModel : ViewModel() {

    private val _shouldRoll = MutableStateFlow(false)
    val shouldRoll = _shouldRoll.asStateFlow()

    private var recentValues = MutableList(100) { 0f }

    fun onAccelerometerChanged(values: List<Float>) {
        val x = values[0]
        val previous = recentValues.last()
        val delta = x - previous

        recentValues.add(x)
        recentValues.removeAt(0)

        if (abs(delta) > 15f) {
            _shouldRoll.value = true
        }
    }

    fun resetRollFlag() {
        _shouldRoll.value = false
    }
}