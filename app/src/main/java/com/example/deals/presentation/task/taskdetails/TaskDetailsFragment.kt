package com.example.deals.presentation.task.taskdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentTaskDetailsBinding
import com.example.deals.domain.model.Task
import com.example.deals.presentation.task.taskchange.TaskChangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : Fragment() {

    private val changeViewModel by viewModels<TaskChangeViewModel>()
    private val binding by viewBinding(FragmentTaskDetailsBinding::bind)

    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val args: TaskDetailsFragmentArgs by navArgs()
        task = args.task

        if (task != null) {
            binding.tvTaskName.text = task!!.name
            binding.tvTaskDeadlineTime.text = task!!.deadlineDate
            binding.tvTaskCommentary.text = task!!.commentary
            binding.checkboxTaskCompletion.isChecked = task!!.isCompleted

            binding.checkboxTaskCompletion.setOnCheckedChangeListener { compoundButton, b ->
                changeViewModel.changeTaskCompletion(
                    binding.checkboxTaskCompletion.isChecked,
                    task!!.id
                )
            }
        }
    }
}