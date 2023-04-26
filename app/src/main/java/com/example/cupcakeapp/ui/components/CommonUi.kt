package com.example.cupcakeapp.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcakeapp.R


@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, subtotal),
        modifier = modifier
    )
}