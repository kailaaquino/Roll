package com.zybooks.roll.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = ["id"],
    childColumns = ["category_id"],
    onDelete = ForeignKey.CASCADE
)])

data class ActivityItem(
    val id: Int = 0,
    val name: String,
    val address: String? = null,
    val notes: String? = null,
    val isCompleted: Boolean = false,
    @ColumnInfo(name = "category_id")
    var categoryId: Long = 0
)

