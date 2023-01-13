package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.michaeldovoh.fixercurrencyconverter.presentation_common.state.CommonScreen

@Composable
fun ConverterScreen(
    viewModel: CurrencyConverterViewModel,
    modifier: Modifier,
) {
    var targetCurrencySymbol by rememberSaveable { mutableStateOf("GHS") }
    var baseCurrencySymbol by rememberSaveable { mutableStateOf("USD") }
    var baseAmount by rememberSaveable { mutableStateOf("1") }
    var swapCurrency by rememberSaveable { mutableStateOf(false) }

    var rate by rememberSaveable {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(24.dp, 0.dp)) {
        Spacer(modifier = Modifier.height(40.dp))
        viewModel.currencyListFlow.collectAsState().value.let { state ->
            CommonScreen(state = state) {
                    currencyListModel->
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    CurrencyPicker(
                        modifier=Modifier.padding(2.dp),
                        readOnly = false,
                        enabled = true,
                        defaultSymbol = baseCurrencySymbol,
                        currencyListModel.items,
                        onSymbolSelected = {
                            baseCurrencySymbol = it
                            viewModel.onCurrencyChanged(
                                it,
                                baseAmount,
                                targetCurrencySymbol
                            )

                        }
                    )

                    IconButton(onClick = {
                        val temp= baseCurrencySymbol
                        baseCurrencySymbol = targetCurrencySymbol
                        targetCurrencySymbol = temp
                        swapCurrency = !swapCurrency



                        if(baseAmount.isNotEmpty() && targetCurrencySymbol.isNotEmpty() && baseCurrencySymbol.isNotEmpty()){
                            viewModel.convert(
                                baseCurrencySymbol,
                                targetCurrencySymbol,
                                baseAmount.toDouble(),
                                date="2023-01-09"
                            )
                        }


                    }) {
                        Icon(
                            imageVector = Icons.Filled.CompareArrows,
                            contentDescription = stringResource(R.string.compare_arrow_description),
                            modifier = Modifier.padding(8.dp),
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }

                    CurrencyPicker(
                        modifier=Modifier.padding(2.dp),
                        readOnly = false,
                        enabled = true,
                        defaultSymbol = targetCurrencySymbol,
                        currencyListModel.items,
                        onSymbolSelected = {
                            targetCurrencySymbol = it
                            viewModel.onCurrencyChanged(
                                baseCurrencySymbol,
                                baseAmount,
                                it
                            )


                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CurrencyRateTextField(
                baseCurrencySymbol,
                modifier.weight(1.0f),
                readOnly = false,
                value = baseAmount,
                enabled = true,
                onAmountChanged = { newBaseAmount ->
                    baseAmount = newBaseAmount
                    // currencyViewModel.updateBase(it)

                    if (newBaseAmount.isNotEmpty() && newBaseAmount.toDoubleOrNull()!=null) {
                        viewModel.convert(
                            baseCurrencySymbol,
                            targetCurrencySymbol,
                            newBaseAmount.toDouble(), date = "2023-01-09"
                        )


                    }
                }
            )
            Spacer(modifier = Modifier
                .height(16.dp)
                .weight(0.1f))
            //base currency picker
            CurrencyRateTextField(
                targetCurrencySymbol,
                modifier.weight(1.0f),
                readOnly = false,
                value = rate,
                /*value = data.convertedAmount,
*/
                enabled = false,

                onAmountChanged = {

                }
            )

        }

    }
    }