package com.zybooks.roll.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.roll.data.RollRepository
import com.zybooks.roll.data.model.ActivityItem
import com.zybooks.roll.data.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeckViewModel(
    private val repo: RollRepository
) : ViewModel() {

    val categories: StateFlow<List<Category>> =
        repo.getCategories()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = emptyList()
            )

    fun getCategoryNameById(categoryId: Long): String {
        return categories.value.find { it.id == categoryId }?.name ?: "Unknown Category"
    }

    fun getCategory(categoryId: Long): Flow<Category?> {
        return repo.getCategory(categoryId)
    }


    fun addCategory(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            val category = Category(name = name)
            repo.addCategory(category)
        }
    }
    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCategory(category)
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateCategory(category)
        }
    }

    fun getActivitiesForCategory(categoryId: Long): Flow<List<ActivityItem>> {
        return repo.getActivitiesForCategory(categoryId)
    }

    fun getActivity(activityId: Long): Flow<ActivityItem?> {
        return repo.getActivity(activityId)
    }

    fun addActivity(
        name: String,
        categoryId: Long,
        address: String? = null,
        notes: String? = null
    ) {
        val newActivity = ActivityItem(
            name = name,
            address = address,
            notes = notes,
            categoryId = categoryId
        )

        viewModelScope.launch(Dispatchers.IO) {
            repo.addActivity(newActivity)
        }
    }
    fun deleteActivity(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteActivity(activity)
        }
    }
    fun updateActivity(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateActivity(activity)
        }
    }

    fun toggleActivityStatus(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val updated = activity.copy(isCompleted = !activity.isCompleted)
            repo.updateActivity(updated)
        }
    }

    fun rollAnyActivity(onResult: (ActivityItem?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val all = repo.getAllActivities().firstOrNull() ?: emptyList()
            val incomplete = all.filter { !it.isCompleted }
            onResult(incomplete.randomOrNull())
        }
    }

    fun rollActivityFromCategory(categoryId: Long, onResult: (ActivityItem?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repo.getActivitiesForCategory(categoryId).firstOrNull() ?: emptyList()
            val incomplete = list.filter { !it.isCompleted }
            onResult(incomplete.randomOrNull())
        }
    }
    suspend fun rollAnyActivitySuspending(): ActivityItem? {
        val all = repo.getAllActivities().firstOrNull() ?: emptyList()
        return all.filter { !it.isCompleted }.randomOrNull()
    }

    suspend fun rollActivityFromCategorySuspending(categoryId: Long): ActivityItem? {
        val list = repo.getActivitiesForCategory(categoryId).firstOrNull() ?: emptyList()
        return list.filter { !it.isCompleted }.randomOrNull()
    }

}