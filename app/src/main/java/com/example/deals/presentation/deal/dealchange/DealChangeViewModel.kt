package com.example.deals.presentation.deal.dealchange

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.usecase.deal.ChangeDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DealChangeViewModel @Inject constructor(
    private val changeDealUseCase: ChangeDealUseCase
): ViewModel() {

    @SuppressLint("CheckResult")
    fun changeDeal(deal: Deal) {
        Single.just(deal)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                changeDealUseCase.changeDeal(deal = deal)
            }, {

            })
    }
}