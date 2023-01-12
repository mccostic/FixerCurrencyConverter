package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    navController: NavController
) {

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

                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
    }