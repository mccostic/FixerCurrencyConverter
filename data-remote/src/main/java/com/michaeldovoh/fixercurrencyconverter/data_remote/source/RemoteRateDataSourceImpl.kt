package com.michaeldovoh.fixercurrencyconverter.data_remote.source

import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.CurrencyService
import com.michaeldovoh.fixercurrencyconverter.data_remote.networking.currency.RateApiModel
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteRateDataSource
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRateDataSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    RemoteRateDataSource {


    override fun getRate(base: String, target: String, date: String): Flow<Rate> = flow {
        emit(currencyService.getRate(date = date, base = base, symbols=target))
    }.map { rateApiModel ->
        convertRate(rateApiModel,target)
    }.catch {
        throw UseCaseException.RateException(it)
    }

    private fun convertRate(rateApiModel: RateApiModel, target: String): Rate {
        return Rate(
            base = rateApiModel.base,
            target = target,
            date = rateApiModel.date,
            timestamp = rateApiModel.timestamp, rate = rateApiModel.rates.getValue(target))
    }



}