package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import androidx.lifecycle.ViewModel
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val useCase: GetCurrenciesUseCase,) : ViewModel() {


}