package com.zybooks.roll.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.zybooks.roll.data.model.Category

class DeckViewModel: ViewModel() {
    private val _categories = mutableStateListOf(
        Category(1, "Restaurants"),
        Category(2, "Hikes"),
        Category(3, "Activities")
    )
    val categories: List<Category> = _categories

    fun addCategory(name: String) {
        val newId = (_categories.maxOfOrNull { it.id } ?: 0) + 1
        _categories.add(Category(newId, name))
    }
}