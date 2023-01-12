package com.michaeldovoh.fixercurrencyconverter.data_local.source

import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyDao: CurrencyDao):
    LocalCurrencyDataSource {
    override fun getCurrencies(): Flow<List<Currency>> =currencyDao.getCurrencies().map {
        currencies-> currencies.map {
            Currency(iso = it.iso, name = it.name)
    }

    }
    override suspend fun addCurrencies(currencies: List<Currency>) = currencyDao.insertCurrencies(currencies.map {
        CurrencyEntity(iso = it.iso, name =it.name)
    })


}