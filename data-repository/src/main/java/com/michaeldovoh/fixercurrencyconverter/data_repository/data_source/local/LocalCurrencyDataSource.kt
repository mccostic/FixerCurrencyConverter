package com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface LocalCurrencyDataSource {
    fun getCurrencies(): Flow<List<Currency>>
    suspend fun addCurrencies(currencies: List<Currency>)
}