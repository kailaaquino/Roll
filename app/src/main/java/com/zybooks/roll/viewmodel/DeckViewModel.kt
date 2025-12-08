package com.zybooks.roll.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.data.model.Category

class DeckViewModel: ViewModel() {
    private val _categories = mutableStateListOf(
        Category(1, "Study Spots"),
        Category(2, "Hikes"),
        Category(3, "Nearby cities")
    )
    val categories: List<Category> = _categories

    private val _activities = mutableStateListOf(
        ActivityItem(id = 1, name = "Scout Coffee", address = "880 Foothill Blvd, San Luis Obispo, CA 93405", "Must try!", categoryId = 1),
        ActivityItem(id = 2, name = "Kreuzberg", address = "685 Higuera St, San Luis Obispo, CA 93401", "Open till 9", categoryId = 1),
        ActivityItem(id = 3, name = "SLO Public Market", address = " 3845 S Higuera St, San Luis Obispo, CA 93401", "Upstairs area has outlets and boba!", categoryId = 1),
        ActivityItem(id = 4, name = "SloDoCo", address = "793 Foothill Blvd, San Luis Obispo, CA 93405", "Open 24/7!", categoryId = 1),
        ActivityItem(id = 5, name = "The P", address = "Muir 127, 1 Grand Ave, San Luis Obispo, CA 93405", "Quick hike on campus", categoryId = 2),
        ActivityItem(id = 6, name = "Bishop Peak", address = "Patricia Drive Entrance", "Steeep", categoryId = 2),
        ActivityItem(id = 7, name = "Cerro / Madonna", address = "Marsh entrance", "Christmas tree up in December", categoryId = 2),
        ActivityItem(id = 8, name = "Ontario Ridge", address = "Shell Beach Entrance", "Super steep but great views and a swing!", categoryId = 2),
        ActivityItem(id = 9, name = "Cambria", address = "Cambria, CA", "Cambria Christmas Market, Hearst Castle", categoryId = 3),
        ActivityItem(id = 10, name = "Cayucos", address = "Cayucos, CA", "Antique stores, Hidden Kitchen for brunch!", categoryId = 3),
        ActivityItem(id = 11, name = "Morro Bay", address = "Morro Bay, CA", "Take pictures by the rock, gift shops", categoryId = 3),
        ActivityItem(id = 12, name = "Solvang", address = "Solvang, CA", "Danish town, pastries, Ostrich Farm", categoryId = 3),
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