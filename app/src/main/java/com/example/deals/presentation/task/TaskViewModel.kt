package com.example.deals.presentation.task

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.domain.usecase.task.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase
): ViewModel() {

    private val _taskListLiveData: MutableLiveData<List<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<List<Task>> = _taskListLiveData

    @SuppressLint("CheckResult")
    fun getTasks(deal: Deal) {
        getAllTasksUseCase.getAllTasks(deal = deal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _taskListLiveData.value = it
            }, {

            })
    }
}