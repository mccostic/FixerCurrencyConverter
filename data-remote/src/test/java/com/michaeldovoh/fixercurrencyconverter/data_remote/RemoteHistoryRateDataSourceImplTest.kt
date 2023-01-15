package com.michaeldovoh.fixercurrencyconverter.data_remote

import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyApiModel
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyService
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.history.HistoryRateApiModel
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.history.HistoryRateService
import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteHistoryRateDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteHistoryRateDataSourceImplTest {
    private val historyService = mock<HistoryRateService>()
    private val historyDataSource = RemoteHistoryRateDataSourceImpl(historyService)


    @ExperimentalCoroutinesApi
    @Test
    fun testGetHistoryRates() = runTest {
        val remoteRates = HistoryRateApiModel(rates = mapOf("2023-01-12" to mapOf("GHS" to 10.0)), success = true, timeseries = true, startDate = "2023-01-12", endDate = "2023-01-15", base = "USD")
        val expectedRates = listOf(HistoryRate(base = "USD", target = "GHS", date = "2023-01-12", rate = 10.0F))
        whenever(historyService.fetchHistory(start_date = "2023-01-12", end_date = "2023-01-15", base = "USD", symbols = "GHS")).thenReturn(remoteRates)
        val result = historyDataSource.getHistoryRate(startDate = "2023-01-12", endDate = "2023-01-15", base = "USD", target = "GHS").first()
        Assert.assertEquals(expectedRates, result)
    }

}