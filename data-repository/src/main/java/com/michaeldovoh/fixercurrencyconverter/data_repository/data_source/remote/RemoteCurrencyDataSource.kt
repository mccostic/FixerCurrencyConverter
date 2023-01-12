package com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.flow.Flow

interface RemoteCurrencyDataSource {
    fun getCurrencies(): Flow<List<Currency>>
}