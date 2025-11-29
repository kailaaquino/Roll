package com.zybooks.roll.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.data.model.Category

class DeckViewModel: ViewModel() {
    private val _categories = mutableStateListOf(
        Category(1, "Restaurants"),
//        Category(2, "Hikes"),
//        Category(3, "Activities")
    )
    val categories: List<Category> = _categories

    private val _activities = mutableStateListOf(
        ActivityItem(id = 1, name = "Pizza", address = "123 Pizza St San Luis Obispo, CA 93405", "Must try!", categoryId = 1),
    )

    val activities: List<ActivityItem> = _activities

    fun addCategory(name: String) {
        val newId = (_categories.maxOfOrNull { it.id } ?: 0) + 1
        _categories.add(Category(newId, name))
    }

    fun getActivitiesForCategory(categoryId: Int?): List<ActivityItem> {
        return _activities.filter { it.categoryId == categoryId }
    }

    fun getCategoryNameById(categoryId: Int): String {
        return categories.find { it.id == categoryId }?.name ?: "Unknown Category"
    }

    fun getActivityById(id: Int): ActivityItem? {
        return activities.find { it.id == id }
    }

    fun addActivity(
        name: String,
        categoryId: Int,
        address: String? = null,
        note: String? = null)
    {
        val newId = (_activities.maxOfOrNull { it.id } ?: 0) + 1
        val activity = ActivityItem(
            id = newId,
            name = name,
            categoryId = categoryId,
            address = address,
            notes = note,
            isCompleted = false
        )
        _activities.add(activity)
    }
}