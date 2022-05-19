package com.example.deals.presentation.task.taskchange

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.domain.usecase.deal.ChangeDealUseCase
import com.example.deals.domain.usecase.task.ChangeTaskUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TaskChangeViewModel @Inject constructor(
    private val changeTaskUseCase: ChangeTaskUseCase
): ViewModel() {

    @SuppressLint("CheckResult")
    fun changeTask(task: Task) {
        Single.just(task)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                changeTaskUseCase.changeTask(task = task)
            }, {

            })
    }
}