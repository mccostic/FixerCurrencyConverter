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
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.kotlin.*
import org.mockito.stubbing.Answer

class CurrencyConverterViewModelTest {

    /*@ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val useCase =
        mock<GetCurrenciesUseCase>()
    private val converter = mock<CurrencyListConverter>()

    private val viewModel: CurrencyConverterViewModel =CurrencyConverterViewModel(
        useCase,
        converter,
    )*/


    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()
    private val useCase =
        mock<GetCurrenciesUseCase>()
    private val converter = mock<CurrencyListConverter>()
    private val viewModel = CurrencyConverterViewModel(
        useCase,
        converter
    )


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        //testDispatcher.cleanupTestCoroutines()
    }




    @ExperimentalCoroutinesApi
    @Test
    fun testLoadPosts() = runTest {
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
      //  assertEquals(uiState, viewModel.currencyListFlow.value)
    }

}