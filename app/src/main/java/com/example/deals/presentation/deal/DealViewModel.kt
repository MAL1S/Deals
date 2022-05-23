package com.example.deals.presentation.deal

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.domain.usecase.deal.GetAllDealsUseCase
import com.example.deals.domain.usecase.deal.RemoveDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DealViewModel @Inject constructor(
    private val getAllDealsUseCase: GetAllDealsUseCase,
    private val removeDealUseCase: RemoveDealUseCase
): ViewModel() {

    private val _dealListLiveData: MutableLiveData<List<Deal>> = MutableLiveData()
    val dealListLiveData: LiveData<List<Deal>> = _dealListLiveData

    @SuppressLint("CheckResult")
    fun getDeals() {
        getAllDealsUseCase.getAllDeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _dealListLiveData.value = it
            }, {

            })
    }

    @SuppressLint("CheckResult")
    fun removeDealById(deal: Deal) {
        Single.just(deal)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                removeDealUseCase.removeDealById(deal = it)
            }, {

            })
    }
}