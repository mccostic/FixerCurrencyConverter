package com.michaeldovoh.fixercurrencyconverter.data_remote

import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyApiModel
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyService
import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.entity.UseCaseException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteCurrencyDataSourceImplTest {
    private val currencyService = mock<CurrencyService>()
    private val currencyDataSource = RemoteCurrencyDataSourceImpl(currencyService)


    @ExperimentalCoroutinesApi
    @Test
    fun testGetCurrencies() = runTest {
        val remoteCurrencies = CurrencyApiModel(list = mapOf("USD" to "United States Dollar"), success = true)
        val expectedCurrencies = listOf(Currency(iso = "USD", name = "United States Dollar"))
        whenever(currencyService.getCurrencies()).thenReturn(remoteCurrencies)
        val result = currencyDataSource.getCurrencies().first()
        Assert.assertEquals(expectedCurrencies, result)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testGetCurrenciesThrowsError() = runTest {
        whenever(currencyService.getCurrencies()).thenThrow(RuntimeException())
        currencyDataSource.getCurrencies().catch {
            Assert.assertTrue(it is UseCaseException.CurrencyException)
        }.collect{

        }
    }
}