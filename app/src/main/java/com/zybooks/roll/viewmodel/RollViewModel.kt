package com.zybooks.roll.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs

class RollViewModel : ViewModel() {

    private val shakeThreshold = 15f
    private val shakeCooldown = 800L
    private var lastShakeTime = 0L

    private val _shouldRoll = MutableStateFlow(false)
    val shouldRoll = _shouldRoll.asStateFlow()

    private var recentValues = MutableList(100) { 0f }

    private var shakeEnabled = false

    fun enableShakeDetection() {
        shakeEnabled = true
    }

    fun disableShakeDetection() {
        shakeEnabled = false
    }

    fun onAccelerometerChanged(values: List<Float>) {
        if (!shakeEnabled) return

        val x = values[0]
        val previous = recentValues.last()
        val delta = x - previous

        recentValues.add(x)
        recentValues.removeAt(0)

        val now = System.currentTimeMillis()

        if (abs(delta) > shakeThreshold && (now - lastShakeTime > shakeCooldown)) {
            lastShakeTime = now
            _shouldRoll.value = true
        }
    }

    fun resetRollFlag() {
        _shouldRoll.value = false
    }
}