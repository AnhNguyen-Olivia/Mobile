package com.example.name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.name.ui.theme.NameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChangeName(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ChangeName(modifier: Modifier = Modifier) {
    val initialName = "World"
    var greetingName by remember { mutableStateOf(initialName) }
    var fieldValue by remember { mutableStateOf(initialName) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello, $greetingName", fontSize = 24.sp)

            OutlinedTextField(
                value = fieldValue,
                onValueChange = { fieldValue = it
                    },
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = {
                    greetingName = fieldValue
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Change")
            }
        }
    }
}