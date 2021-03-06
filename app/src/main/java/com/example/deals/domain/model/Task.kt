package com.example.deals.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "task")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var commentary: String,
    var deadlineDate: String,
    var isCompleted: Boolean,
    var dealID: Int
): Parcelable