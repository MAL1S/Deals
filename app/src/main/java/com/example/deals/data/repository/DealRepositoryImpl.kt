package com.example.deals.data.repository

import com.example.deals.data.local.DealDao
import com.example.deals.domain.model.Deal
import com.example.deals.domain.repository.DealRepository
import io.reactivex.Single
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(
    private val dealDao: DealDao
) : DealRepository {
    override fun save(deal: Deal) {
        dealDao.insertDeal(deal = deal)
    }

    override fun change(deal: Deal) {
        dealDao.updateDeal(name = deal.name, id = deal.id)
    }

    override fun remove(deal: Deal) {
        dealDao.removeDealById(id = deal.id)
    }

    override fun getDeals(): Single<List<Deal>> {
        return dealDao.getAllDeals()
    }
}