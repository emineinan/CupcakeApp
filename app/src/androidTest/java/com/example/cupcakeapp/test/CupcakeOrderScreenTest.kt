package com.example.cupcakeapp.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.StartOrderScreen
import com.example.cupcakeapp.ui.order.OrderUiState
import org.junit.Rule
import org.junit.Test
import com.example.cupcakeapp.data.DataSource.quantityOptions
import com.example.cupcakeapp.ui.SelectOptionScreen
import com.example.cupcakeapp.ui.order.OrderSummaryScreen

class CupcakeOrderScreenTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = OrderUiState(
        quantity = 6,
        flavor = "Vanilla",
        date = "Thu Apr 27",
        price = "$100",
        pickupOptions = listOf()
    )

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = quantityOptions,
                onNextButtonClicked = {}
            )
        }

        quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun selectOptionsScreen_verifyContent() {
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = flavours)
        }

        flavours.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun summaryScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> }
            )
        }

        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, fakeOrderUiState.price)
        )
    }
}