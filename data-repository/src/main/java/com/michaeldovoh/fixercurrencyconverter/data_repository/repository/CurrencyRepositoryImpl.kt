package com.michaeldovoh.fixercurrencyconverter.data_repository.repository





import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteCurrencyDataSource: RemoteCurrencyDataSource,
    private val localCurrencyDataSource: LocalCurrencyDataSource
) : CurrencyRepository {

    override fun getCurrencies(): Flow<List<Currency>> =
        localCurrencyDataSource.getCurrencies().onEach {

            if (it.isEmpty()) {

                remoteCurrencyDataSource.getCurrencies().onEach { it2 ->
                    localCurrencyDataSource.addCurrencies(it2)
                }.collect()
            }


        }
}
