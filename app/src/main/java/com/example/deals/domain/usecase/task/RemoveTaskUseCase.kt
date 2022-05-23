package com.example.deals.domain.usecase.task

import com.example.deals.data.repository.TaskRepositoryImpl
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import javax.inject.Inject

class RemoveTaskUseCase @Inject constructor(
    private val taskRepositoryImpl: TaskRepositoryImpl
) {

    fun removeTaskById(task: Task) {
        taskRepositoryImpl.remove(task = task)
    }
}