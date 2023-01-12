package com.michaeldovoh.fixercurrencyconverter.domain.entity


data class HistoryRate(val base:String,
                       val date:String,
                       val target:String,
                       val rate:Float)