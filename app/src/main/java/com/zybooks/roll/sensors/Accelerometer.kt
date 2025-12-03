package com.zybooks.roll.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlin.math.sqrt

class Accelerometer(
    private val onShake: () -> Unit
) : SensorEventListener {

    private val shakeThreshold = 12f
    private val shakeCooldown = 500L
    private var lastShakeTime = 0L

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        val (x, y, z) = event.values

        val gForce = sqrt(x * x + y * y + z * z)

        if (gForce > shakeThreshold) {
            val now = System.currentTimeMillis()
            if (now - lastShakeTime > shakeCooldown) {
                lastShakeTime = now
                onShake()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}
