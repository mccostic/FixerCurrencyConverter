package com.michaeldovoh.fixercurrencyconverter.data_repository

import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.CurrencyRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CurrencyRepositoryImplTest {
    private val remoteCurrencyDataSource = mock<RemoteCurrencyDataSource>()
    private val localCurrencyDataSource = mock<LocalCurrencyDataSource>()
    private val repositoryImpl = CurrencyRepositoryImpl(remoteCurrencyDataSource, localCurrencyDataSource)


    @ExperimentalCoroutinesApi
    @Test
    fun `getCurrencies from remote data source when local data source is empty`() = runTest {
        val currencies = listOf<Currency>()
        val remoteCurrencies = listOf(Currency(iso = "USD", name = "United States Dollar"))
        whenever(localCurrencyDataSource.getCurrencies()).thenReturn(flowOf(currencies))
        whenever(remoteCurrencyDataSource.getCurrencies()).thenReturn(flowOf(listOf(Currency(iso = "USD", name = "United States Dollar"))))
        val result = repositoryImpl.getCurrencies().first()
        Assert.assertEquals(currencies, result)
        verify(localCurrencyDataSource).addCurrencies(remoteCurrencies )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getCurrencies from local data source when not empty`() = runTest {
        val currencies =  listOf(Currency(iso = "USD", name = "United States Dollar"))
        whenever(localCurrencyDataSource.getCurrencies()).thenReturn(flowOf(currencies))
        val result = repositoryImpl.getCurrencies().first()
        Assert.assertEquals(currencies, result)
        verify(localCurrencyDataSource).getCurrencies()
    }
}