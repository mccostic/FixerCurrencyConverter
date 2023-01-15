package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetRateUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val useCase: GetCurrenciesUseCase,
    private val currencyConverter: CurrencyListConverter,
    private val rateConverter: RateConverter,
    private val getRateUseCase: GetRateUseCase,
    ) : ViewModel() {

    private val _currencyListFlow =
        MutableStateFlow<UiState<CurrencyListModel>>(UiState.Loading)
    val currencyListFlow: StateFlow<UiState<CurrencyListModel>> get()= _currencyListFlow

    private val _convertedRateFlow =
        MutableStateFlow<UiState<List<RateModel>>>(UiState.Initial)
    val convertedRateFlow: StateFlow<UiState<List<RateModel>>> = _convertedRateFlow

        init {
        loadCurrencies()
    }
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

    fun onCurrencyChanged(baseCurrencySymbol: String, baseAmount: String, targetCurrencySymbol: String) {


        convert(baseCurrencySymbol = baseCurrencySymbol, targetCurrencySymbol = targetCurrencySymbol, baseAmount = baseAmount.toDouble(),
            date = "2023-01-09")
    }

    fun convert(baseCurrencySymbol: String, targetCurrencySymbol: String, baseAmount: Double,date:String) {
        viewModelScope.launch {
            getRateUseCase.execute(GetRateUseCase.Request(base = baseCurrencySymbol,target=targetCurrencySymbol,date=date))
                .map {
                    Log.d("convert_currency", it.toString())
                    rateConverter.convert(it)
                }
                .collect {
                    if(it is UiState.Error){
                        val its = it.errorMessage
                        Log.d("convertERRR", its)
                    }


                    Log.d("convert", it.toString())
                    //_convertedRateFlow.value = it
                    _convertedRateFlow.value = it
                }
        }
    }

}