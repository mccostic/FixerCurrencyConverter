package com.michaeldovoh.fixercurrencyconverter.data_repository.repository



import android.util.Log
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalRateDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteRateDataSource
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.repository.RateRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val remoteRateDataSource: RemoteRateDataSource,
    private val localRateDataSource: LocalRateDataSource
) : RateRepository {



    override fun getRate(base: String, target: String, date: String): Flow<List<Rate>> =
        localRateDataSource.getRate(base=base,target=target,date=date).distinctUntilChanged().onEach {

            if (it.isEmpty()) {
                Log.d("localRateDataSource",it.toString())
                remoteRateDataSource.getRate(base = base, target = target, date = date)
                    .onEach { it2 ->
                        Log.d("remoteRateDataSource",it2.toString())
                        localRateDataSource.addRate(it2)
                    }.catch { error->  Log.d("getRateError",error.toString())}.distinctUntilChanged().collect()

            }
        }

    }
        /*return remoteCurrencyDataSource.getCurrencies().onEach {
            localCurrencyDataSource.addCurrencies(it)
        }*/
