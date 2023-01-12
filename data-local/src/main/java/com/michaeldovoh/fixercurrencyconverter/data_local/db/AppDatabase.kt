package com.michaeldovoh.fixercurrencyconverter.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyDao
import com.michaeldovoh.fixercurrencyconverter.data_local.db.currency.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun currencyDao(): CurrencyDao
}