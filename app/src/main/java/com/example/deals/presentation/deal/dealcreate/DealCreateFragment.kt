package com.example.deals.presentation.deal.dealcreate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentDealCreateBinding
import com.example.deals.databinding.FragmentDealListBinding
import com.example.deals.domain.model.Deal
import com.example.deals.presentation.deal.DealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealCreateFragment : Fragment() {

    private val viewModel by viewModels<DealCreateViewModel>()
    private val binding by viewBinding(FragmentDealCreateBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.btnDealConfirm.setOnClickListener {
            viewModel.saveDeal(Deal(name = binding.editTextDealNameCreate.text.toString()))
            view?.findNavController()?.popBackStack()
        }
    }
}