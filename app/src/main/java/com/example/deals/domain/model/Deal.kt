package com.example.deals.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "deal")
@Parcelize
data class Deal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String
): Parcelable
