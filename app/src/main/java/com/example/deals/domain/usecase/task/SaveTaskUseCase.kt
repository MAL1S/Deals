package com.example.deals.domain.usecase.task

import com.example.deals.data.repository.TaskRepositoryImpl
import com.example.deals.domain.model.Task
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val taskRepositoryImpl: TaskRepositoryImpl
) {

    fun saveTask(task: Task) {
        taskRepositoryImpl.save(task = task)
    }
}