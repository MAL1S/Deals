package com.example.deals.domain.usecase.deal

import com.example.deals.data.repository.DealRepositoryImpl
import com.example.deals.domain.model.Deal
import javax.inject.Inject

class ChangeDealUseCase @Inject constructor(
    private val dealRepositoryImpl: DealRepositoryImpl
) {

    fun changeDeal(deal: Deal) {
        dealRepositoryImpl.change(deal = deal)
    }
}