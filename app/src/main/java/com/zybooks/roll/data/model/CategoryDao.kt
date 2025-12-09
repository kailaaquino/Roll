package com.zybooks.roll.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategory(id: Long): Flow<Category?>

    @Query("SELECT * FROM Category ORDER BY name COLLATE NOCASE")
    fun getCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(category: Category): Long

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)
}

