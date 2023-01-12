package com.michaeldovoh.fixercurrencyconverter.domain.entity

data class Rate(val base:String, val target:String, val date:String, val timestamp: Long,val rate:Double)