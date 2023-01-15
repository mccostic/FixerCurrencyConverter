package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val useCase: GetCurrenciesUseCase,private val currencyConverter:
CurrencyListConverter) : ViewModel() {

    private val _currencyListFlow =
        MutableStateFlow<UiState<CurrencyListModel>>(UiState.Loading)
    val currencyListFlow: StateFlow<UiState<CurrencyListModel>> get()= _currencyListFlow

//    init {
//        loadCurrencies()
//    }
     fun loadCurrencies() {
        viewModelScope.launch {
            useCase.execute(GetCurrenciesUseCase.Request)
                .map {
                    currencyConverter.convert(it)
                }
                .collect {

                    _currencyListFlow.value = it
                    print("juyuihojijhhjnkujhjnilkjhnkj,nlk,jhmnk,jmnk,jmnkj,"+_currencyListFlow.value.javaClass)
                }
        }
    }

    fun onCurrencyChanged(baseCurrencySymbol: String, baseAmount: String, it: String) {

    }

    fun convert(
        baseCurrencySymbol: String,
        targetCurrencySymbol: String,
        toDouble: Double,
        date: String
    ) {

    }
}