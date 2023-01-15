package com.michaeldovoh.fixercurrencyconverter.data_local

import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity
import com.michaeldovoh.fixercurrencyconverter.data_local.source.LocalCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalCurrencyDataSourceImplTest {
    private val currencyDao = mock<CurrencyDao>()
    private val currencyDataSource = LocalCurrencyDataSourceImpl(currencyDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCurrencies() = runTest {
        val localCurrencies = listOf(CurrencyEntity(iso = "USD", name = "United States Dollar"))
        val expectedCurrencies = listOf(Currency(iso = "USD", name = "United States Dollar"))
        whenever(currencyDao.getCurrencies()).thenReturn(flowOf(localCurrencies))
        val result = currencyDataSource.getCurrencies().first()
        Assert.assertEquals(expectedCurrencies, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddCurrencies() = runTest {
        val localCurrencies = listOf(CurrencyEntity(iso = "USD", name = "United States Dollar"))
        val currencies = listOf(Currency(iso = "USD", name = "United States Dollar"))
        currencyDataSource.addCurrencies(currencies)
        verify(currencyDao).insertCurrencies(localCurrencies)
    }
}