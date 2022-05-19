package com.example.deals.domain.repository

import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import io.reactivex.Single

interface TaskRepository {

    fun save(task: Task)

    fun change(task: Task)

    fun remove(task: Task)

    fun getTasks(deal: Deal): Single<List<Task>>
}