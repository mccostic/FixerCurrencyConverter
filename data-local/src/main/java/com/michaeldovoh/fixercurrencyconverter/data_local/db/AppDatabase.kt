package com.michaeldovoh.fixercurrencyconverter.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity
import com.michaeldovoh.fixercurrencyconverter.data_local.db.rate.RateDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.rate.RateEntity

@Database(entities = [CurrencyEntity::class, RateEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun currencyDao(): CurrencyDao
    abstract fun rateDao(): RateDao

}