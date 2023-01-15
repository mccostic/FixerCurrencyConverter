package com.michaeldovoh.fixercurrencyconverter.domain.repository

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface RateRepository {

     fun getRate(base:String,target:String,date:String): Flow<List<Rate>>
}