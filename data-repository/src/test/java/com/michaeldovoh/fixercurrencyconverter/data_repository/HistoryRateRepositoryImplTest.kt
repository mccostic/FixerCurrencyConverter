package com.michaeldovoh.fixercurrencyconverter.data_repository

import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteHistoryRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.HistoryRateRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.RateRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HistoryRateRepositoryImplTest {
    private val remoteHistoryRateDataSource = mock<RemoteHistoryRateDataSource>()
    private val repositoryImpl = HistoryRateRepositoryImpl(remoteHistoryRateDataSource)


    @ExperimentalCoroutinesApi
    @Test
    fun `getHistoryRate from remote data source`() = runTest {
        val rates = listOf<HistoryRate>()
        val rate = Rate(base = "USD", target = "GHS", date = "2023-01-15", timestamp = 1L, rate = 10.0)
        whenever(repositoryImpl.getHistoryRate(base = "USD", target = "GHS", startDate = "2023-01-12", endDate = "2023-01-15")).thenReturn(
            flowOf(rates)
        )
        val result = repositoryImpl.getHistoryRate(base = "USD", target = "GHS", endDate = "2023-01-15", startDate = "2023-01-12").first()
        Assert.assertEquals(rates, result)
        verify(remoteHistoryRateDataSource).getHistoryRate(base = "USD", target = "GHS", endDate = "2023-01-15", startDate = "2023-01-12")
    }
}