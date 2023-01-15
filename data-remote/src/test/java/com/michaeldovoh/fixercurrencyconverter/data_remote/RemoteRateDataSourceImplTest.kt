package com.michaeldovoh.fixercurrencyconverter.data_remote

import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyApiModel
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyService
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.RateApiModel
import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteRateDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.entity.UseCaseException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteRateDataSourceImplTest {
    private val currencyService = mock<CurrencyService>()
    private val currencyDataSource = RemoteRateDataSourceImpl(currencyService)


    @ExperimentalCoroutinesApi
    @Test
    fun testGetRate() = runTest {
        val remoteRate = RateApiModel(rates = mapOf("GHS" to 10.0), success = true, date = "2023-01-12",
            historical = true, timestamp = 1L, base = "USD")
        val expectedRate = Rate(base = "USD", timestamp = 1L, target = "GHS", date = "2023-01-12", rate = 10.0)
        whenever(currencyService.getRate(date = "2023-01-12", base = "USD", symbols = "GHS")).thenReturn(remoteRate)
        val result = currencyDataSource.getRate(date = "2023-01-12", base = "USD", target = "GHS").first()
        Assert.assertEquals(expectedRate, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGeRateThrowsError() = runTest {
        whenever(currencyService.getCurrencies()).thenThrow(RuntimeException())
        currencyDataSource.getRate(date = "2023-01-12", base = "USD", target = "GHS").catch {
            Assert.assertTrue(it is UseCaseException.RateException)
        }.collect{

        }
    }
}