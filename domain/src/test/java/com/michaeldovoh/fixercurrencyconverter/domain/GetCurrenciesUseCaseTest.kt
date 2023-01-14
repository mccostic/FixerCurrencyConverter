package com.michaeldovoh.fixercurrencyconverter.domain

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.repository.CurrencyRepository
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCurrenciesUseCaseTest {
    private val currencyRepository = mock<CurrencyRepository>()
    private val useCase = GetCurrenciesUseCase(
        mock(),
        currencyRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = GetCurrenciesUseCase.Request
        val post = listOf( Currency(iso = "USD", name = "United States Dollar"))
        whenever(currencyRepository.getCurrencies()).thenReturn(flowOf(post))
        val response = useCase.process(request).first()
        assertEquals(GetCurrenciesUseCase.Response(post), response)
    }
}