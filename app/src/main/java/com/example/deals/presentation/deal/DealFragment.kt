package com.example.deals.presentation.deal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentDealListBinding
import com.example.deals.databinding.FragmentTaskListBinding
import com.example.deals.domain.model.Deal
import com.example.deals.presentation.task.TaskFragmentDirections
import com.example.deals.presentation.task.TaskRecyclerAdapter
import com.example.deals.presentation.task.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealFragment : Fragment(), OnDealClickedListener {

    private val viewModel by viewModels<DealViewModel>()
    private val binding by viewBinding(FragmentDealListBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        init()
    }

    private fun init() {
        viewModel.dealListLiveData.observe(viewLifecycleOwner) {
            Log.d("BRUH", "$it")
            val adapter = DealRecyclerAdapter(it, this)
            binding.rcvDeals.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        binding.btnDealAdd.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_dealFragment_to_dealCreateFragment)
        }
        viewModel.getDeals()
    }

    override fun onDealClicked(deal: Deal) {
        val action = DealFragmentDirections.actionDealFragmentToTaskFragment(deal)
        action.deal = deal
        view?.findNavController()?.navigate(action)
    }
}