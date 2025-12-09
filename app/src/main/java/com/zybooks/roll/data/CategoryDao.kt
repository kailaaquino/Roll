package com.zybooks.roll.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zybooks.roll.data.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategory(id: Long): Flow<Category?>

    @Query("SELECT * FROM Category ORDER BY name COLLATE NOCASE")
    fun getCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun addCategory(category: Category): Long

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)
}