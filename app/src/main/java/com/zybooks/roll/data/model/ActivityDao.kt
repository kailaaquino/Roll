package com.zybooks.roll.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM ActivityItem WHERE id = :id")
    fun getActivity(id: Long): Flow<ActivityItem?>

    @Query("SELECT * FROM ActivityItem WHERE category_id = :categoryId ORDER BY id")
    fun getActivities(categoryId: Long): Flow<List<ActivityItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addActivity(activity: ActivityItem): Long

    @Update
    fun updateActivity(activity: ActivityItem)

    @Delete
    fun deleteActivity(activity: ActivityItem)
}