package com.michaeldovoh.fixercurrencyconverter.domain

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.repository.CurrencyRepository
import com.michaeldovoh.fixercurrencyconverter.domain.repository.RateRepository
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetRateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRateUseCaseTest {

    private val rateRepository = mock<RateRepository>()
    private val useCase = GetRateUseCase(
        mock(),
        rateRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = GetRateUseCase.Request(base = "USD", target = "GHS", date = "2023-01-15")
        val rates = listOf( Rate(base = "USD", target = "GHS", date = "2023-01-15", rate = 10.0, timestamp = 1L))
        whenever(rateRepository.getRate(base = "USD", target = "GHS", date = "2023-01-15")).thenReturn(flowOf(rates))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetRateUseCase.Response(rates), response)
    }
}