package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import com.michaeldovoh.fixercurrencyconverter.domain.entity.Rate
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetRateUseCase
import org.junit.Assert
import org.junit.Test

class RateConverterTest {
    private val converter = RateConverter()

    @Test
    fun testConvertSuccess() {
        val response = GetRateUseCase.Response(
            rate = listOf(
                Rate(
                    base = "USD",
                    target = "GHS",
                    date = "2023-01-15",
                    timestamp = 1L,
                    rate = 10.0
                )
            )
        )
        val result = converter.convertSuccess(response)
        Assert.assertEquals(
            listOf(
                RateModel(
                    base = "USD",
                    target = "GHS",
                    date = "2023-01-15",
                    timestamp = 1L,
                    rate = 10.0
                )
            ), result
        )
    }
}