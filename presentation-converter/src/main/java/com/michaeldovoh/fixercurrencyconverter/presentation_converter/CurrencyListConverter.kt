package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import android.content.Context
import com.michaeldovoh.fixercurrencyconverter.domain.usecase.GetCurrenciesUseCase
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetCurrenciesUseCase.Response, CurrencyListModel>() {

    override fun convertSuccess(data: GetCurrenciesUseCase.Response): CurrencyListModel {

        return CurrencyListModel(
            items = data.currencies.map {
                CurrencyListItemModel(
                    iso = it.iso,
                    name = it.name
                )
            },
        )
    }
}