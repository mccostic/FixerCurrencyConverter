package com.michaeldovoh.fixercurrencyconverter.data_local.injection



import com.michaeldovoh.fixercurrencyconverter.data_local.source.LocalCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_local.source.LocalRateDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalCurrencyDataSource
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.local.LocalRateDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindCurrencyDataSource(lostDataSourceImpl: LocalCurrencyDataSourceImpl): LocalCurrencyDataSource

    @Binds
    abstract fun bindRateDataSource(localRateDataSourceImpl: LocalRateDataSourceImpl): LocalRateDataSource

}