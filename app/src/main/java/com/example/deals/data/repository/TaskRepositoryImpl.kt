package com.example.deals.data.repository

import android.util.Log
import com.example.deals.data.local.TaskDao
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.domain.repository.TaskRepository
import io.reactivex.Single
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {
    override fun save(task: Task) {
        taskDao.insertTask(task = task)
    }

    override fun change(task: Task) {
//        taskDao.updateTask(
//            name = task.name,
//            commentary = task.commentary,
//            deadlineDate = task.deadlineDate,
//            id = task.id
//        )
        taskDao.updateTask(task = task)
    }

    override fun change(completion: Boolean, id: Int) {
        //taskDao.updateTaskCompletion(completion = completion, id = id)
        //taskDao.updateTask(task = task)
    }

    override fun remove(task: Task) {
        taskDao.removeTaskById(id = task.id)
    }

    override fun getTasks(deal: Deal): Single<List<Task>> {
        return taskDao.getAllTasks(dealID = deal.id)
    }
}