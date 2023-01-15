package com.michaeldovoh.fixercurrencyconverter.domain.repository

import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import kotlinx.coroutines.flow.Flow

interface HistoryRateRepository {
     fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):Flow<List<HistoryRate>>
}