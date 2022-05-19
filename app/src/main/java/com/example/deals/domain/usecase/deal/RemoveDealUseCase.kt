package com.example.deals.domain.usecase.deal

import com.example.deals.data.repository.DealRepositoryImpl
import com.example.deals.domain.model.Deal
import javax.inject.Inject

class RemoveDealUseCase  @Inject constructor(
    private val dealRepositoryImpl: DealRepositoryImpl
) {

    fun removeDealById(deal: Deal) {
        dealRepositoryImpl.remove(deal = deal)
    }
}