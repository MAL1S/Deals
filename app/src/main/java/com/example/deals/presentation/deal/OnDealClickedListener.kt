package com.example.deals.presentation.deal

import com.example.deals.domain.model.Deal

interface OnDealClickedListener {

    fun onDealClicked(deal: Deal)
}