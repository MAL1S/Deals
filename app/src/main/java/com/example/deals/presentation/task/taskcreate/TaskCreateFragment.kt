package com.example.deals.presentation.task.taskcreate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentTaskChangeBinding
import com.example.deals.databinding.FragmentTaskCreateBinding
import com.example.deals.domain.model.Task
import com.example.deals.presentation.task.taskchange.TaskChangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskCreateFragment : Fragment() {

    private val viewModel by viewModels<TaskCreateViewModel>()
    private val binding by viewBinding(FragmentTaskCreateBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.btnTaskAdd.setOnClickListener {
            viewModel.createTask(task = Task(
                name = binding.editTextTaskNameCreate.text.toString(),
                commentary = binding.editTextTaskCommentaryCreate.text.toString(),
                deadlineDate = binding.tvDealDateCreate.text.toString(),
                isCompleted = false
            ))
        }
    }
}