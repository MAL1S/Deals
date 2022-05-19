package com.example.deals.presentation.deal.dealcreate

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.usecase.deal.SaveDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DealCreateViewModel @Inject constructor(
    private val saveDealUseCase: SaveDealUseCase
): ViewModel() {

    @SuppressLint("CheckResult")
    fun saveDeal(deal: Deal) {
        Single.just(deal)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                saveDealUseCase.saveDeal(deal = deal)
            }, {

            })
    }
}