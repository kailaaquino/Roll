package com.zybooks.roll.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.zybooks.roll.data.model.Category
import com.zybooks.roll.data.model.ActivityItem

class RollRepository(context: Context) {
    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
//            CoroutineScope(Dispatchers.IO).launch {
//                addStarterData()
//            }
        }
    }
    private val database: RollDatabase = Room.databaseBuilder(
        context,
        RollDatabase::class.java,
        "roll.db"
    )
        .addCallback(databaseCallback)
        .build()

    private val categoryDao = database.categoryDao()
    private val activityDao = database.activityDao()

    fun getCategory(categoryId: Long) = categoryDao.getCategory(categoryId)
    fun getCategories() = categoryDao.getCategories()
    fun addCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.addCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.deleteCategory(category)
        }
    }

    fun updateCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.updateCategory(category)
        }
    }

    fun getActivity(activityId: Long) = activityDao.getActivity(activityId)
    fun getActivities(categoryId: Long) = activityDao.getActivities(categoryId)
    fun addActivity(activity: ActivityItem) {
        CoroutineScope(Dispatchers.IO).launch {
            activityDao.addActivity(activity)
        }
    }

    fun deleteActivity(activity: ActivityItem) {
        CoroutineScope(Dispatchers.IO).launch {
            activityDao.deleteActivity(activity)
        }
    }

    fun updateActivity(activity: ActivityItem) {
        CoroutineScope(Dispatchers.IO).launch {
            activityDao.updateActivity(activity)
        }
    }


}
