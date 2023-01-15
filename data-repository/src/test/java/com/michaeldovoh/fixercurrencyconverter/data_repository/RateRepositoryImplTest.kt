package com.michaeldovoh.fixercurrencyconverter.data_repository

import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.CurrencyRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.RateRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
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

class RateRepositoryImplTest {
    private val remoteRateDataSource = mock<RemoteRateDataSource>()
    private val localRateDataSource = mock<LocalRateDataSource>()
    private val repositoryImpl = RateRepositoryImpl(remoteRateDataSource, localRateDataSource)


    @ExperimentalCoroutinesApi
    @Test
    fun `getRate from remote data source when local data source is empty`() = runTest {
        val rates = listOf<Rate>()
        val rate = Rate(base = "USD", target = "GHS", date = "2023-01-15", timestamp = 1L, rate = 10.0)
        whenever(localRateDataSource.getRate(base = "USD", target = "GHS", date = "2023-01-15")).thenReturn(flowOf(rates))
        whenever(remoteRateDataSource.getRate(base = "USD", target = "GHS", date = "2023-01-15")).thenReturn(flowOf((rate)))
        val result = repositoryImpl.getRate(base = "USD", target = "GHS", date = "2023-01-15").first()
        Assert.assertEquals(rates, result)
        verify(localRateDataSource).addRate(rate)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `getRate from local data source when not empty`() = runTest {
        val rates =  listOf(Rate(base = "USD", target = "GHS", date = "2023-01-15", timestamp = 1L, rate = 10.0))
        whenever(localRateDataSource.getRate(base = "USD", target = "GHS", date = "2023-01-15")).thenReturn(flowOf(rates))
        val result = repositoryImpl.getRate(base = "USD", target = "GHS", date = "2023-01-15").first()
        Assert.assertEquals(rates, result)
        verify(localRateDataSource).getRate(base = "USD", target = "GHS", date = "2023-01-15")
    }
}