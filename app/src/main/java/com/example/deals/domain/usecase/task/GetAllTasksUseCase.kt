package com.example.deals.domain.usecase.task

import com.example.deals.data.repository.TaskRepositoryImpl
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import io.reactivex.Single
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepositoryImpl: TaskRepositoryImpl
) {

    fun getAllTasks(deal: Deal): Single<List<Task>> {
        return taskRepositoryImpl.getTasks(deal = deal)
    }
}