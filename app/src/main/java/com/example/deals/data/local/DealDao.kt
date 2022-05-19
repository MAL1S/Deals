package com.example.deals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deals.domain.model.Deal
import io.reactivex.Single

@Dao
interface DealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeal(deal: Deal)

    @Query("UPDATE deal SET name = :name WHERE id = :id")
    fun updateDeal(name: String, id: Int)

    @Query("SELECT * FROM deal")
    fun getAllDeals(): Single<List<Deal>>

    @Query("DELETE FROM deal WHERE id = :id")
    fun removeDealById(id: Int)
}