package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyListConverterTest {
    private val converter = CurrencyListConverter()

    @Test
    fun testConvertSuccess() {
        val response = GetCurrenciesUseCase.Response(
            currencies = listOf(
                Currency(
                  iso = "USD", name = "United States Dollar"
                )
            )
        )
        val result = converter.convertSuccess(response)
        assertEquals(
            CurrencyListModel(
                items = listOf(
                    CurrencyListItemModel(
                       iso = "USD",
                        name = "United States Dollar"
                    )
                ),
            ), result
        )
    }
}