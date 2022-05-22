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

    @Query("UPDATE task SET name = :name AND commentary = :commentary AND deadlineDate = :deadlineDate AND isCompleted = :completion WHERE id = :id")
    fun updateTask(name: String, commentary: String, deadlineDate: String, id: Int, completion: Boolean)

    @Query("UPDATE task SET isCompleted = :completion WHERE id = :id")
    fun updateTaskCompletion( completion: Boolean, id: Int)

    @Query("SELECT * FROM task WHERE dealID = :dealID")
    fun getAllTasks(dealID: Int): Single<List<Task>>

    @Query("DELETE FROM task WHERE id = :id")
    fun removeTaskById(id: Int)
}