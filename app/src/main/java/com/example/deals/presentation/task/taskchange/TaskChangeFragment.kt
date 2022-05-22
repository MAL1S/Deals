package com.example.deals.presentation.task.taskchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentTaskChangeBinding
import com.example.deals.databinding.FragmentTaskListBinding
import com.example.deals.domain.model.Task
import com.example.deals.presentation.task.TaskFragmentArgs
import com.example.deals.presentation.task.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskChangeFragment : Fragment() {

    private val viewModel by viewModels<TaskChangeViewModel>()
    private val binding by viewBinding(FragmentTaskChangeBinding::bind)

    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg: TaskChangeFragmentArgs by navArgs()
        task = arg.task

        init()
    }

    private fun init() {
        binding.apply {
            if (task != null) {
                editTextTaskNameChange.setText(task!!.name)
                editTextTaskCommentaryChange.setText(task!!.commentary)
                tvDealDateChange.text = task!!.deadlineDate

                btnTaskChange.setOnClickListener {
                    task!!.name = binding.editTextTaskNameChange.text.toString()
                    task!!.commentary = binding.editTextTaskCommentaryChange.text.toString()
                    task!!.deadlineDate = binding.tvDealDateChange.text.toString()

                    viewModel.changeTask(task = task!!)
                    findNavController().popBackStack()
                }
            }
        }
    }
}