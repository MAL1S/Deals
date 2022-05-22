package com.example.deals.presentation.task

import com.example.deals.domain.model.Task

interface OnTaskClickedListener {

    fun onTaskClicked(task: Task)

    fun onCompletionChanged(task: Task)
}