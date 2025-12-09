package com.zybooks.roll.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,

    @ColumnInfo(name = "created")
    var creationTime: Long = System.currentTimeMillis()

)
// val Activities: List<ActivityItem> = emptyList() ADD LATER
