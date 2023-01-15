package com.michaeldovoh.fixercurrencyconverter.presentation_history

import androidx.lifecycle.ViewModel
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetHistoryRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val useCase: GetHistoryRateUseCase,
                        private val historyRateListConverter: HistoryRateListConverter,)
    :ViewModel(){


}