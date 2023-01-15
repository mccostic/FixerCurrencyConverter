package com.michaeldovoh.fixercurrencyconverter.presentation_history

import android.content.Context
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetHistoryRateUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.CommonResultConverter
import javax.inject.Inject

class HistoryRateListConverter @Inject constructor() :
    CommonResultConverter<GetHistoryRateUseCase.Response, HistoryRateListModel>() {

    override fun convertSuccess(data: GetHistoryRateUseCase.Response): HistoryRateListModel {

        return HistoryRateListModel(
            items = data.historyRates.map {
                HistoryRateListItemModel(
                   base = it.base,
                    target = it.target,
                    date = it.date,
                    rate = it.rate
                )
            },
        )
    }
}