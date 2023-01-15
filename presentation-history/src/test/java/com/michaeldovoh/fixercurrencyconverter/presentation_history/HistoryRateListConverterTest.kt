package com.michaeldovoh.fixercurrencyconverter.presentation_history

import com.michaeldovoh.fixercurrencyconverter.domain.entity.HistoryRate
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetHistoryRateUseCase
import org.junit.Assert
import org.junit.Test

class HistoryRateListConverterTest {

    private val converter = HistoryRateListConverter()

    @Test
    fun testConvertSuccess() {
        val response = GetHistoryRateUseCase.Response(
            historyRates = listOf(
                HistoryRate(
                 base = "USD",
                 target = "GHS",
                 rate = 10.0F,
                 date = "2023-01-12"
                )
            )
        )
        val result = converter.convertSuccess(response)
        Assert.assertEquals(
            HistoryRateListModel(
                items = listOf(
                    HistoryRateListItemModel(base = "USD", target = "GHS", rate = 10.0F, date = "2023-01-12")
                )
            ), result
        )
    }
}