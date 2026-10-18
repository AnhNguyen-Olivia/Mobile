package com.example.ui_testing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwoCounters(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "$count",
                fontSize = 72.sp,
                modifier = Modifier.testTag("positiveCount")
            )
            Text(
                text = "${-count}",
                fontSize = 72.sp,
                modifier = Modifier.testTag("negativeCount")
            )
        }
        Button(
            onClick = { count += 1 },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Click")
        }
    }
}