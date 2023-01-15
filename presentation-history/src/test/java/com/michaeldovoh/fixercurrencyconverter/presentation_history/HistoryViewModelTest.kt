package com.michaeldovoh.fixercurrencyconverter.presentation_history

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Result
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetHistoryRateUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HistoryViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = DispatcherRule()

    private val useCase = mock<GetHistoryRateUseCase>()
    private val converter = mock<HistoryRateListConverter>()

    private lateinit var viewModel: HistoryViewModel
    @Before
    fun setUp() {
        viewModel =HistoryViewModel(
            useCase,
            converter
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetHistoryRates() = runTest {
        assertEquals(UiState.Loading, viewModel.historyRatesFlow.value)
        val uiState = mock<UiState<HistoryRateListModel>>()
        val result = mock<Result<GetHistoryRateUseCase.Response>>()
        whenever(useCase.execute(GetHistoryRateUseCase.Request(base ="USD", target = "GHS", startDate = "2023-01-12", endDate = "2023-01-15"))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.getHistoryRates(base = "USD", startDate = "2023-01-15",endDate = "2023-01-15", target = "GHS")
        //assertEquals(uiState, viewModel.historyRatesFlow.value)
    }


}