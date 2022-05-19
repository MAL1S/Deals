package com.example.deals.presentation.task

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentTaskListBinding
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment : Fragment(), OnTaskClickedListener {

    private val viewModel by viewModels<TaskViewModel>()
    private val binding by viewBinding(FragmentTaskListBinding::bind)

    private var deal: Deal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg: TaskFragmentArgs by navArgs()
        deal = arg.deal

        requireActivity().findViewById<TextView>(R.id.toolbar_text).text = deal?.name

        init()
    }

    private fun init() {
        viewModel.taskListLiveData.observe(viewLifecycleOwner) {
            val adapter = TaskRecyclerAdapter(it, this)
            binding.rcvTasks.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        if (deal != null) {
            viewModel.getTasks(deal = deal!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.edit, menu)
    }

    override fun onTaskClicked(task: Task) {
        val action = TaskFragmentDirections.actionTaskFragmentToTaskDetailsFragment(task)
        action.task = task
        view?.findNavController()?.navigate(action)
    }
}