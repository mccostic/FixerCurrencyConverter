package com.michaeldovoh.fixercurrencyconverter.data_local.db

import androidx.room.Database
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase {
    abstract fun currencyDao(): CurrencyDao
}