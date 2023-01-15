package com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote


import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface RemoteRateDataSource {
    fun getRate(base:String,target:String,date:String):Flow<Rate>
}