package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import androidx.compose.runtime.collectAsState
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Result
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetRateUseCase
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.kotlin.*
import org.mockito.stubbing.Answer


class CurrencyConverterViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mock<GetCurrenciesUseCase>()

    private val useCaseRateUseCase = mock<GetRateUseCase>()
    private val converter = mock<CurrencyListConverter>()

    private val rateConverter = mock<RateConverter>()

    private lateinit var viewModel: CurrencyConverterViewModel
    @Before
    fun setUp() {
        viewModel =CurrencyConverterViewModel(
        useCase,
        converter,
        rateConverter,
        useCaseRateUseCase
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadCurrencies() = runTest {
        assertEquals(UiState.Loading, viewModel.currencyListFlow.value)
        val uiState = mock<UiState<CurrencyListModel>>()

        val result = mock<Result<GetCurrenciesUseCase.Response>>()
        whenever(
            useCase.execute(
                GetCurrenciesUseCase.Request
            )
        ).thenReturn(
            flowOf(
                result
            )
        )


        whenever(converter.convert(result)).thenReturn(uiState)

       viewModel.loadCurrencies()
        //println("viewModel.currencyListFlow.value = ${viewModel.currencyListFlow.value}")

       //verify(converter.convert(result), atLeastOnce())

        println("state="+uiState)
       assertEquals(uiState, viewModel.currencyListFlow.value)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testConvertCurrency() = runTest {
        assertEquals(UiState.Loading, viewModel.currencyListFlow.value)
        val uiState = mock<UiState<List<RateModel>>>()
        val result = mock<Result<GetRateUseCase.Response>>()
        whenever(useCaseRateUseCase.execute(GetRateUseCase.Request(base ="USD", target = "GHS", date = "2023-01-15"))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(rateConverter.convert(result)).thenReturn(uiState)
        viewModel.convert(baseAmount = 1.0, baseCurrencySymbol = "USD", date = "2023-01-15", targetCurrencySymbol = "GHS")
        assertEquals(uiState, viewModel.convertedRateFlow.value)
    }

}