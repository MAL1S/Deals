package com.example.deals.presentation.deal.dealchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentDealChangeBinding
import com.example.deals.databinding.FragmentDealCreateBinding
import com.example.deals.domain.model.Deal
import com.example.deals.presentation.deal.dealcreate.DealCreateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealChangeFragment : Fragment() {

    private val viewModel by viewModels<DealChangeViewModel>()
    private val binding by viewBinding(FragmentDealChangeBinding::bind)

    private var deal: Deal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg: DealChangeFragmentArgs by navArgs()
        deal = arg.deal

        init()
    }

    private fun init() {
        binding.btnDealChange.setOnClickListener {
            if (deal != null) {
                deal!!.name = binding.editTextDealNameChange.text.toString()
                viewModel.changeDeal(deal = deal!!)
            }
        }
    }
}