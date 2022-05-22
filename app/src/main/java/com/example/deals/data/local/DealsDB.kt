package com.example.deals.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task

@Database(entities = [Deal::class, Task::class], version = 2)
abstract class DealsDB: RoomDatabase() {
    abstract fun getDealDao(): DealDao
    abstract fun getTaskDao(): TaskDao
}