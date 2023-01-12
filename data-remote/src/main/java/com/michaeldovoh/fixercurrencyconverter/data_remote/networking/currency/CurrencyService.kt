package com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("/fixer/symbols")
    suspend fun getCurrencies(): CurrencyApiModel

    @GET(value = "/fixer/{date}")
    suspend fun getRate(@Path("date") date:String,
                        @Query("symbols") symbols:String,
                        @Query("base") base:String): RateApiModel
}