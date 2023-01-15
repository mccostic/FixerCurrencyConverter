package com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local


import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface LocalRateDataSource {

    suspend fun addRate(rate: Rate)

    fun getRate(base:String,target:String,date:String):Flow<List<Rate>>
}