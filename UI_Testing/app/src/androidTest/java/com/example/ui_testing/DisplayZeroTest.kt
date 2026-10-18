package com.example.ui_testing

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class DisplayZeroTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysZeroInitially() {
        composeTestRule.setContent {
            TwoCounters()
        }

        composeTestRule.onNodeWithTag("positiveCount").assertTextEquals("0")
        composeTestRule.onNodeWithTag("negativeCount").assertTextEquals("0")
    }
}