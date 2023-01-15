package com.michaeldovoh.fixercurrencyconverter.data_local

import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity
import com.michaeldovoh.fixercurrencyconverter.data_local.db.rate.RateDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.rate.RateEntity
import com.michaeldovoh.fixercurrencyconverter.data_local.source.LocalCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_local.source.LocalRateDataSourceImpl
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

class LocalRateDataSourceImplTest {

    private val rateDao = mock<RateDao>()
    private val rateDataSource = LocalRateDataSourceImpl(rateDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRate() = runTest {
        val base = "USD"
        val target = "GHS"
        val date ="2023-01-12"
        val id = "${base}:${target}:${date}"
        val localRate = listOf(RateEntity(base = base, target = target, id=id, date = date, timestamp = 1L, rate = 10.0))
        val expectedRates = listOf(Rate(base = base, target = target, date =date, timestamp = 1L, rate = 10.0))
        whenever(rateDao.getRate(id = id)).thenReturn(flowOf(localRate))
        val result = rateDataSource.getRate(base = base, target =target, date = date).first()
        Assert.assertEquals(expectedRates, result)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testAddRate() = runTest {
        val base = "USD"
        val target = "GHS"
        val date ="2023-01-12"
        val id = "${base}:${target}:${date}"
        val localRates = RateEntity(base = base, target = target, id=id, date = date, timestamp = 1L, rate = 10.0)
        val rates = Rate(base = base, target = target, date =date, timestamp = 1L, rate = 10.0)
        rateDataSource.addRate(rates)
        verify(rateDao).insertRate(localRates)
    }
}