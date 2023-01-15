package com.michaeldovoh.fixercurrencyconverter.domain

import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.repository.HistoryRateRepository
import com.michaeldovoh.fixercurrencyconverter.domain.repository.RateRepository
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetHistoryRateUseCase
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetRateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetHistoryRateUseCaseTest {


    private val historyRepository = mock<HistoryRateRepository>()
    private val useCase = GetHistoryRateUseCase(
        mock(),
        historyRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = GetHistoryRateUseCase.Request(base = "USD", target = "GHS", startDate = "2023-01-12", endDate = "2023-01-15")
        val rates = listOf( HistoryRate(base = "USD", target = "GHS", date = "2023-01-15", rate = 10.0F))
        whenever(historyRepository.getHistoryRate(base = "USD", target = "GHS", startDate = "2023-01-12", endDate = "2023-01-15")).thenReturn(
            flowOf(rates)
        )
        val response = useCase.process(request).first()
        Assert.assertEquals(GetHistoryRateUseCase.Response(rates), response)
    }
}