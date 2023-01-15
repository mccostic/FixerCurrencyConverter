package com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote


import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import kotlinx.coroutines.flow.Flow

interface RemoteHistoryRateDataSource {

   fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):Flow<List<HistoryRate>>

}