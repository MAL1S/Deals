package com.example.deals.domain.repository

import com.example.deals.domain.model.Deal
import io.reactivex.Single

interface DealRepository {

    fun save(deal: Deal)

    fun change(deal: Deal)

    fun remove(deal: Deal)

    fun getDeals(): Single<List<Deal>>
}