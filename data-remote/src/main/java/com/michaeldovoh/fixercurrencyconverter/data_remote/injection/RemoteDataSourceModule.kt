package com.michaeldovoh.fixercurrencyconverter.data_remote.injection

import com.michaeldovoh.fixercurrencyconverter.data_remote.source.RemoteCurrencyDataSourceImpl
import com.michaeldovoh.fixercurrencyconverter.data_repository.data_source.remote.RemoteCurrencyDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCurrencyDataSource(remoteCurrencyDataSourceImpl: RemoteCurrencyDataSourceImpl): RemoteCurrencyDataSource


}