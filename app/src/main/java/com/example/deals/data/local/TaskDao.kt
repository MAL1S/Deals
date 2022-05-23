package com.example.deals.data.local

import androidx.room.*
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import io.reactivex.Single

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

//    @Query("UPDATE task SET name = :name AND commentary = :commentary AND deadlineDate = :deadlineDate WHERE id = :id")
//    fun updateTask(name: String, commentary: String, deadlineDate: String, id: Int)
//
//    @Query("UPDATE task SET isCompleted = :completion WHERE id = :id")
//    fun updateTaskCompletion( completion: Boolean, id: Int)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM task WHERE dealID = :dealID")
    fun getAllTasks(dealID: Int): Single<List<Task>>

    @Query("DELETE FROM task WHERE id = :id")
    fun removeTaskById(id: Int)

    @Query("DELETE FROM task WHERE dealID = :dealID")
    fun removeTaskByDealId(dealID: Int)
}