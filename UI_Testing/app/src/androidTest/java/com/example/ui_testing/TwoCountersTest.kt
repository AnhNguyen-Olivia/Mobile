package com.example.ui_testing

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class TwoCountersTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingButtonIncrementsCount() {
        composeTestRule.setContent {
            TwoCounters()
        }

        composeTestRule.onNodeWithTag("positiveCount").assertTextEquals("0")
        composeTestRule.onNodeWithTag("negativeCount").assertTextEquals("0")

        composeTestRule.onNodeWithText("Click").performClick()

        composeTestRule.onNodeWithTag("positiveCount").assertTextEquals("1")
        composeTestRule.onNodeWithTag("negativeCount").assertTextEquals("-1")
    }
}