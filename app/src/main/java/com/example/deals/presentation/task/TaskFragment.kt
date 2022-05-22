package com.example.deals.presentation.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.example.deals.presentation.main.MainActivity
import com.example.deals.presentation.task.taskchange.TaskChangeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment : Fragment(), OnTaskClickedListener {

    private val viewModel by viewModels<TaskViewModel>()
    private val changeViewModel by viewModels<TaskChangeViewModel>()
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

        setHasOptionsMenu(true)

        val arg: TaskFragmentArgs by navArgs()
        deal = arg.deal

        init()
    }

    override fun onResume() {
        super.onResume()

        (requireActivity() as AppCompatActivity).supportActionBar?.title = deal?.name
        MainActivity.State.drawerToggleLiveData.value = true
        //(requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
//        toolbar.setnavigation
//        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        viewModel.getTasks(deal = deal!!)
    }

    private fun init() {
        viewModel.taskListLiveData.observe(viewLifecycleOwner) {
            Log.d("BRUH", "$it")
            val adapter = TaskRecyclerAdapter(it, this)
            binding.rcvTasks.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        Toast.makeText(requireContext(), "$deal", Toast.LENGTH_SHORT).show()
        if (deal != null) {
            viewModel.getTasks(deal = deal!!)

            binding.btnTaskAdd.setOnClickListener {
                val action = TaskFragmentDirections.actionTaskFragmentToTaskCreateFragment(deal!!)
                action.deal = deal!!
                view?.findNavController()?.navigate(action)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val action = TaskFragmentDirections.actionTaskFragmentToDealChangeFragment(deal!!)
            action.deal = deal!!
            view?.findNavController()?.navigate(action)
        }
        return true
    }

    override fun onTaskClicked(task: Task) {
        val action = TaskFragmentDirections.actionTaskFragmentToTaskDetailsFragment(task)
        action.task = task
        view?.findNavController()?.navigate(action)
    }

    override fun onCompletionChanged(task: Task) {
        changeViewModel.changeTask(task = task)
    }
}