package com.zybooks.roll.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.data.model.Category

class DeckViewModel: ViewModel() {
    private val _categories = mutableStateListOf(
        Category(1, "Restaurants"),
        Category(2, "Hikes")
    )
    val categories: List<Category> = _categories

    private val _activities = mutableStateListOf(
        ActivityItem(id = 1, name = "Pizza", address = "123 Pizza St San Luis Obispo, CA 93405", "Must try!", categoryId = 1),
        ActivityItem(id = 2, name = "BBQ", address = "123 BBQ St San Luis Obispo, CA 93405", categoryId = 1),
        ActivityItem(id = 3, name = "Bishop", address = "123 Cerro St San Luis Obispo, CA 93405", "Must hike!", categoryId = 2),
        ActivityItem(id = 4, name = "The P", address = "123 Grand St San Luis Obispo, CA 93405", "Easy hike!", categoryId = 2),

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
    fun toggleActivityStatus(activityId: Int) {
        val index = _activities.indexOfFirst { it.id == activityId }
        if (index != -1) {
            val old = _activities[index]
            _activities[index] = old.copy(
                isCompleted = !old.isCompleted
            )
        }
    }

    fun rollAnyActivity(): ActivityItem? {
        if (_activities.isEmpty()) {
            return null
        }

        val incomplete = _activities.filter { !it.isCompleted }

        if (incomplete.isEmpty()) {
            return null
        }

        return incomplete.random()
    }
//        val incomplete = _activities.filter { !it.isCompleted }
//        return incomplete.randomOrNull()


    fun rollActivityFromCategory(categoryId: Int): ActivityItem? {
        val activitiesInCategory = _activities.filter { it.categoryId == categoryId }
        if (activitiesInCategory.isEmpty()) {
            return null
        }

        val incomplete = activitiesInCategory.filter { !it.isCompleted }
        return if (incomplete.isNotEmpty()) {
            incomplete.random()
        } else {
            null
        }
    }
}