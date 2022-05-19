package com.example.deals.domain.usecase.deal

import com.example.deals.data.repository.DealRepositoryImpl
import com.example.deals.data.repository.TaskRepositoryImpl
import com.example.deals.domain.model.Deal
import io.reactivex.Single
import javax.inject.Inject

class GetAllDealsUseCase @Inject constructor(
    private val dealRepositoryImpl: DealRepositoryImpl
) {
    fun getAllDeals(): Single<List<Deal>> {
        return dealRepositoryImpl.getDeals()
    }
}