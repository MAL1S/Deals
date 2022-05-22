package com.example.deals.presentation.task.taskcreate

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.domain.usecase.deal.ChangeDealUseCase
import com.example.deals.domain.usecase.task.ChangeTaskUseCase
import com.example.deals.domain.usecase.task.SaveTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val saveTaskUseCase: SaveTaskUseCase
): ViewModel() {

    @SuppressLint("CheckResult")
    fun createTask(task: Task) {
        Single.just(task)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                saveTaskUseCase.saveTask(task = task)
            }, {

            })
    }
}