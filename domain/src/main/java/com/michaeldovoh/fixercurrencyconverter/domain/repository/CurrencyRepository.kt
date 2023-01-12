package com.michaeldovoh.fixercurrencyconverter.domain.repository

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {
    fun getCurrencies(): Flow<List<Currency>>
}