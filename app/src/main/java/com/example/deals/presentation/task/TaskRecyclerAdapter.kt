package com.example.deals.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.DealItemBinding
import com.example.deals.databinding.TaskItemBinding
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task

class TaskRecyclerAdapter(
    private val tasks: List<Task>,
    private val onTaskClickedListener: OnTaskClickedListener
) : RecyclerView.Adapter<TaskRecyclerAdapter.TaskRecyclerViewHolder>() {

    class TaskRecyclerViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onTaskClickedListener: OnTaskClickedListener
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.task_item, parent, false)) {

        private val binding by viewBinding(TaskItemBinding::bind)

        fun bind(task: Task) {
            binding.tvTaskName.text = task.name
            binding.tvTaskCommentary.text = task.commentary
            binding.checkboxTask.isChecked = task.isCompleted
            binding.tvTaskDeadlineTime.text = task.deadlineDate

            itemView.setOnClickListener {
                onTaskClickedListener.onTaskClicked(task = task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskRecyclerViewHolder(inflater, parent, onTaskClickedListener)
    }

    override fun onBindViewHolder(holder: TaskRecyclerViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size
}

