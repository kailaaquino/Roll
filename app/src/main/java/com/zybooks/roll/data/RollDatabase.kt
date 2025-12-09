package com.zybooks.roll.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zybooks.roll.data.model.Category
import com.zybooks.roll.data.model.ActivityItem

@Database(entities = [Category::class, ActivityItem::class], version = 1)
abstract class RollDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun activityDao(): ActivityDao
}