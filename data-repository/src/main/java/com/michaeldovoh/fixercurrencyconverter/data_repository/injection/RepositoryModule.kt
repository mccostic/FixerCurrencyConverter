package com.michaeldovoh.fixercurrencyconverter.data_repository.injection


import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.CurrencyRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.data_repository.repository.RateRepositoryImpl
import com.michaeldovoh.fixercurrencyconverter.domain.repository.CurrencyRepository
import com.michaeldovoh.fixercurrencyconverter.domain.repository.RateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    abstract fun bindRateRepository(rateRepositoryImpl: RateRepositoryImpl): RateRepository
}