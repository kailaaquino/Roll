package com.zybooks.roll.data.model


data class ActivityItem(
    val id: Int = 0,
    val name: String,
    val address: String? = null,
    val notes: String? = null,
    val isCompleted: Boolean = false,
    val categoryId: Int
)

