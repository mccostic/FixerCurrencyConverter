package com.michaeldovoh.fixercurrencyconverter.presentation_converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConverterScreen(
    viewModel: CurrencyConverterViewModel,
    modifier: Modifier,
    navController: NavController
) {

    Column(modifier = Modifier.padding(24.dp, 0.dp)) {
        Spacer(modifier = Modifier.height(40.dp))

    }
    }