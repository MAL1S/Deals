package com.example.deals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import io.reactivex.Single

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("UPDATE task SET name = :name AND commentary = :commentary AND deadlineDate = :deadlineDate WHERE id = :id")
    fun updateDeal(name: String, commentary: String, deadlineDate: String, id: Int)

    @Query("SELECT * FROM task")
    fun getAllTasks(): Single<List<Task>>

    @Query("DELETE FROM task WHERE id = :id")
    fun removeTaskById(id: Int)
}