package com.michaeldovoh.fixercurrencyconverter.domain.entity

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {
    class CurrencyException(cause: Throwable) : UseCaseException(cause)
}