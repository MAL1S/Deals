package com.example.deals.di

import android.content.Context
import androidx.room.Room
import com.example.deals.data.local.DealDao
import com.example.deals.data.local.DealsDB
import com.example.deals.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DealsDB {
        return Room.databaseBuilder(
            context,
            DealsDB::class.java,
            "deals_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDealDao(dealDB: DealsDB): DealDao {
        return dealDB.getDealDao()
    }

    @Provides
    fun provideTaskDao(dealDB: DealsDB): TaskDao {
        return dealDB.getTaskDao()
    }
}