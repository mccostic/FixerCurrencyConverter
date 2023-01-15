package com.michaeldovoh.fixercurrencyconverter.presentation_history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.michaeldovoh.fixercurrencyconverter.presentation_common.navigation.HistoryInput


@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    modifier: Modifier,
    navController: NavController,
    input: HistoryInput
) {

    val base by rememberSaveable {
        mutableStateOf(input.baseCurrency)
    }
    val target by rememberSaveable {
        mutableStateOf(input.targetCurrency)
    }

}